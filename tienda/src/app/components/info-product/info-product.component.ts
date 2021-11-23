import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-info-product',
  templateUrl: './info-product.component.html',
  styleUrls: ['./info-product.component.css']
})
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})

// MOD:WILMERN - CHATBOT
// export class AppComponent {
//   title = 'my first app';
//   ngOnInit() {
//     (function(d, m){
//         var kommunicateSettings = 
//             {"appId":"<YOUR APP-ID>","popupWidget":true,"automaticChatOpenOnNavigation":true};
//         var s = document.createElement("script"); s.type = "text/javascript"; s.async = true;
//         s.src = "https://widget.kommunicate.io/v2/kommunicate.app";
//         var h = document.getElementsByTagName("head")[0]; h.appendChild(s);
//         window.kommunicate = m; m._globals = kommunicateSettings;
//     })(document, window.kommunicate || {});
//   }
// }
export class InfoProductComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
