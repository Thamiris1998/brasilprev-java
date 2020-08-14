## Thamiris Vasconsellos Lopes
### Brasilprev - Desafio Java

 **What you’ll need**
 
 * JDK 1.8 or later
 * Spring Boot
 * Gradle
 * Docker
 * Google Sdk -> Used to deploy Dockerfile in AppEngine
 
 ##### Project published in url
  URL: https://thamiris-lopes.uc.r.appspot.com/virtualstore/*
  
 ##### Create Credentials - Authentication API instruction
  Method	| Path	| body {JSON} 
  --- | --- | --- |:---:|:---:|
  POST	| /login | { "username": "admin", "password": "password"}
  
  OBS: Response Bearer token in headers Authorization
  
 ##### Product API instruction PRODUCT
  Method	| Path	| Description	| User authenticated {Bearer} | Available from UI
  --- | --- | --- |:---:|:---:|
  GET	| /virtualstore/product	| Get all products data	| × | ×
  GET	| /virtualstore/product/{id}	| Get specified product data	| × | ×
  POST	| /virtualstore/product	| Create new product data	| × | ×
  PUT	| /virtualstore/product/{id}	| Update specified product data	| × | ×
  DELETE	| /virtualstore/product/{id[]}	| Delete specified product data	| × | ×
  
 ##### Product API instruction CLIENT
  Method	| Path	| Description	| User authenticated {Bearer} | Available from UI
  --- | --- | --- |:---:|:---:|
  GET	| /virtualstore/client	| Get all clients data	| × | ×
  GET	| /virtualstore/client/{id}	| Get specified client data	| × | ×
  POST	| /virtualstore/client	| Create new client data	| × | ×
  PUT	| /virtualstore/product/{id}	| Update client product data	| × | ×
  DELETE	| /virtualstore/product/{id[]}	| Delete client product data	| × | ×
  
 ##### Product API instruction ORDER
  Method	| Path	| Description	| User authenticated {Bearer}| Available from UI
  --- | --- | --- |:---:|:---:|
  POST	| /virtualstore/order	| Create new order data	| × | ×
  PUT	| /virtualstore/order/{id}	| Update specified order data	| × | ×




