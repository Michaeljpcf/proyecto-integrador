package com.springboot.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.springboot.entity.Product;
import com.springboot.entity.ProductOrder;
import com.springboot.security.entity.User;
import com.springboot.security.entity.UserPrimary;
import com.springboot.service.ProductOrderService;
import com.springboot.service.ProductService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ProductRestController {
	
	@Autowired
	private ProductService productService;
	

	@GetMapping("/listProducts")
	@ResponseBody
	public ResponseEntity<List<Product>> findAll() {
		List<Product> lista = productService.findAll();
		return ResponseEntity.ok(lista);
	}
	
	
	
	@GetMapping("/findProduct/{id}")
	public Product show(@PathVariable Integer id) {
		return productService.findById(id);
	}
	
	
	@GetMapping("/getProduct/{id}")
	public Optional<Product> getProductById(@PathVariable Integer id) {
		return productService.getById(id);
	}
	
	@PostMapping("/newProduct")	
	@ResponseBody
	public ResponseEntity<Product> insertProduct(@RequestBody Product obj, Authentication authentication) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		var usuarioPrincipal = (UserPrimary) authentication.getPrincipal();
		User user = new User();
		user.setIdUser(usuarioPrincipal.getIdUser());
		obj.setUser(user);
		Product objSalida = productService.insertProduct(obj);
		
		if (objSalida == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(objSalida);
		}		
		
	}
	
	@DeleteMapping("/deleteProduct/{id}")
    @ResponseBody
    public ResponseEntity<Product> delete(@PathVariable("id")int idProduct){
        Optional<Product> optProduct = productService.getById(idProduct);
        if(optProduct.isPresent()) {
        	Product product = productService.findById(idProduct);
        	String namePictureOld = product.getImage();
        	if(namePictureOld != null && namePictureOld.length() > 0) {
				Path rutaPictureOld = Paths.get("uploads//products").resolve(namePictureOld).toAbsolutePath();
				File filePictureOld = rutaPictureOld.toFile();
				if (filePictureOld.exists() && filePictureOld.canRead()) {
					filePictureOld.delete();
				}
			}
            productService.deleteProduct(idProduct);
            return ResponseEntity.ok(optProduct.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }

    }	
	
	@PutMapping("/updateProduct")	
	@ResponseBody
	public ResponseEntity<String> updateProduct(@RequestBody Product obj) {
		
		Product objSalida = productService.insertProduct(obj);
		String mensaje = "Se actualizó correctamente "+obj.getId();
		
		if (objSalida == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(mensaje);
		}		
		
	}
	
	
	@PostMapping("/products/upload")
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file, @RequestParam("id") Integer id) {
		Map<String, Object> response = new HashMap<>();
		
		Product product = productService.findById(id);
		
		if (!file.isEmpty()) {
			String nameFile = UUID.randomUUID().toString() + "_" + file.getOriginalFilename().replace(" ", "");
			Path rutaFile = Paths.get("uploads//products").resolve(nameFile).toAbsolutePath();
			
			try {
				Files.copy(file.getInputStream(), rutaFile);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del producto " + nameFile);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
			}
			
			String namePictureOld = product.getImage();
			
			if(namePictureOld != null && namePictureOld.length() > 0) {
				Path rutaPictureOld = Paths.get("uploads//products").resolve(nameFile).toAbsolutePath();
				File filePictureOld = rutaPictureOld.toFile();
				if (filePictureOld.exists() && filePictureOld.canRead()) {
					filePictureOld.delete();
				}
			}
			
			product.setImage(nameFile);
			productService.insertProduct(product);
			
			response.put("product", product);
			response.put("mensaje", "Has subido la imagen con éxito: " + nameFile);
		}
		
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@GetMapping("/uploads/img/{namePicture:.+}")
	public ResponseEntity<Resource> viewPicture(@PathVariable String namePicture) {
		Path rutaPictureOld = Paths.get("uploads//products").resolve(namePicture).toAbsolutePath();
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaPictureOld.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		if (!recurso.exists() && !recurso.isReadable()) {
			throw new RuntimeException("Error no se pudo cargar la imagen: " + namePicture);
		}
		HttpHeaders cabeceras = new HttpHeaders();
		cabeceras.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabeceras, HttpStatus.OK);
	}
	
	
	
	
	
	
	@RequestMapping(value = "/newProductt",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})	
	@ResponseBody
	public ResponseEntity<String> insertProductPhoto(@RequestPart Product obj,
			Authentication authentication,
			@RequestPart("images")List<MultipartFile> images ) throws IOException {
		
		var usuarioPrincipal = (UserPrimary) authentication.getPrincipal();
		User user = new User();
		user.setIdUser(usuarioPrincipal.getIdUser());
		obj.setUser(user);
		obj.setStock(obj.getStock()-1);
		productService.insertProductImages(obj, images);
	
		return new ResponseEntity<String>("El archivo fue cargado",HttpStatus.OK);		
	}
	
	
	@RequestMapping(value = "/getImage/{id}", produces = MediaType.IMAGE_JPEG_VALUE/*,consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}*/)	
	@ResponseBody
	public ResponseEntity<byte[]> getPhotoByProductId(@PathVariable("id") int idProduct,
			MultipartHttpServletRequest request )throws IOException {
		
		byte[] lstImages = productService.getProductImageByProductId(idProduct);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_JPEG);
		
		
			return new ResponseEntity<byte[]>(lstImages, headers, HttpStatus.OK);
		}
	
	
	//List de todos los elementos en el S3 bucket
	@GetMapping("/ImagesList")
	public ResponseEntity<List<String>> listFil(){
		return new ResponseEntity<List<String>>(productService.getObjectFromS3(),HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/downloadImage")
	public ResponseEntity<Resource> download(@RequestParam("key")String key){
		InputStreamResource resource = new InputStreamResource(productService.downloadFile(key));
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+key+"\"").body(resource);
	}
	
	
	@GetMapping(value ="/getImgProductByProductId/{idProduct}")
	
	public String getPhotoByIdProduct(@PathVariable("idProduct")int idProduct){
		String objFinal = productService.getImageByProductId(idProduct);
		return objFinal;
	}
	
	
	
	@GetMapping("/getListProductsWithImage")
	@ResponseBody
	public ResponseEntity<List<Product>> getListProductsWithImage(){
		List<Product> lstProduct = productService.getProductsWith1Image();
		return ResponseEntity.ok(lstProduct);
	}
	
	
	@GetMapping("/getListProductUsingParams")
	@ResponseBody
	public ResponseEntity<Map<String,Object>> getListProductWithParams(
			@RequestParam(name = "name",defaultValue = ""  ,required = false)String name,
			@RequestParam(name = "cat",defaultValue = "0"  ,required = false)int cat,
			@RequestParam(name = "price1",defaultValue = "0.0",required = false)Double price1,
			@RequestParam(name = "price2",defaultValue = "0.0",required = false)Double price2){
		
		Map<String,Object> mapFinal = new HashMap<>();
		try {
			List<Product> lstProduct = productService.getProductosWithParams("%"+name+"%", cat, price1,price2);
			if(CollectionUtils.isEmpty(lstProduct)) {
				mapFinal.put("mensaje", "No existe datos para la consulta");
			}else {
				mapFinal.put("list", lstProduct);
				mapFinal.put("mensaje", "Existe = "+lstProduct.size());
			}
		} catch (Exception e) {
			e.printStackTrace();
			mapFinal.put("mensaje", "Ocurrio un error, No existe datos para mostrar");
		}
		return ResponseEntity.ok(mapFinal);
	}

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
