package com.springboot.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.springboot.entity.Product;
import com.springboot.entity.ProductImages;
import com.springboot.repository.ProductImagesRepository;
import com.springboot.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${aws.s3.bucket}")
	private String bucketName;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductImagesRepository proImagesRepository;

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public Product findById(Integer id) {
		Optional<Product> pro = productRepository.findById(id);
		Date today = new Date();
		
		Calendar c = Calendar.getInstance();
		c.setTime(today);
		c.add(Calendar.DATE, 10);
		today = c.getTime();
		pro.get().setDeliveryRangeDate(today);
		 List<String>lstImgs = proImagesRepository.findListofProductsById(id);
		pro.get().setLstProductImages(lstImgs);
					
		return pro.get();
	}

	@Override
	public Product insertProduct(Product obj) {
		return productRepository.save(obj);
	}

	@Override
	public void deleteProduct(int idProduct) {
		productRepository.deleteById(idProduct);
	}

	@Override
	public Optional<Product> getById(int idProduct) {
		Optional<Product> pro = productRepository.findById(idProduct);

		return pro;
	}

	
	
	@Override
	@Transactional
	public void insertProductImages(Product obj, List<MultipartFile> lstImages) throws IOException {
		Product objProduct = null;
		objProduct = productRepository.save(obj);
		
		
		for (MultipartFile aux : lstImages) {
			ProductImages objImages = new ProductImages();
			File mainFile = new File(aux.getOriginalFilename());
			FileOutputStream stream = new FileOutputStream(mainFile);
			stream.write(aux.getBytes());
			String newFileName = System.currentTimeMillis() + "_" + mainFile.getName();

			log.info("Subiendo archivo con el nombre.... " + newFileName);
			PutObjectRequest request = new PutObjectRequest(bucketName, newFileName, mainFile).withCannedAcl(CannedAccessControlList.PublicRead);
			stream.close();
			amazonS3.putObject(request);
			String uri = amazonS3.getUrl(bucketName, newFileName).toString();
			
			objImages.setProduct(objProduct);
			objImages.setImages(uri);
			proImagesRepository.save(objImages);

		}


	}

	@Override
	public byte[] getProductImageByProductId(int id) {
		return proImagesRepository.findTop1ImagesByProducto_Id(id);
	}

	@Override
	public List<String> getObjectFromS3() {
		ListObjectsV2Result result = amazonS3.listObjectsV2(bucketName);
		List<S3ObjectSummary> objects = result.getObjectSummaries();
		List<String> list = objects.stream().map(item -> {
			return item.getKey();
		}).collect(Collectors.toList());
		
		return list;

	}

	@Override
	public InputStream downloadFile(String key) {
		S3Object object = amazonS3.getObject(bucketName,key);
		return object.getObjectContent();
	}

	@Override
	public String getImageByProductId(int productId) {
		return proImagesRepository.findTop1ImagesByProducto_IdStr(productId);
	}

	@Override
	public List<Product> getProductsWith1Image() {
		List<Product> productList = productRepository.findAll();
		if(CollectionUtils.isEmpty(productList)) {
			log.error("No se encontro ningun listado en el metodo de Product LIST");
		}
		for(Product pro : productList) {
			String  aux = proImagesRepository.findTop1ImagesByProducto_IdStr(pro.getId());
			pro.setImage(aux);			
		}
		return productList;
	}

	@Override
	public List<Product> getProductosWithParams(String name, int cat, int subCat, double price1, double price2) {
		List<Product> productList =  productRepository.listProductWithParams(name, cat,subCat, price1,price2);
		if(CollectionUtils.isEmpty(productList)) {
			log.error("No se encontro ningun listado en el metodo de Product LIST");
		}
		for(Product pro : productList) {
			String  aux = proImagesRepository.findTop1ImagesByProducto_IdStr(pro.getId());
			pro.setImage(aux);			
		}
		return productList;
	}

	@Override
	public List<Product> getProductByIdSession(int id) {
		List<Product> lstProducts = productRepository.findAllByUserIdUser(id);
		for(Product pro : lstProducts) {
			String  aux = proImagesRepository.findTop1ImagesByProducto_IdStr(pro.getId());
			pro.setImage(aux);	
		}
		return lstProducts;
	}

	
	
}
