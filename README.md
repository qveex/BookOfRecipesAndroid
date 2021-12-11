### Course project in the discipline Object Oriented Programming on the topic "Book of recipes"     

In the project uses:    
- `Android SDK` :O
- `Jetpack Compose`
- `Room`
- `Dagger - Hilt`
- `MVVM`
- Single Screen Pattern     


------------------------------------------
      
All db relationship represent in Relationship.kt        
Physical database model:    
| ------------  | **db model** | ------------- |
| :---:  | :---:  | :---: |
| |![db](https://sun9-20.userapi.com/impg/LQOpnxlGd6dMKQy23UgWHGtfH5kPvrc7TqAQeg/1GHyIrz0ZOI.jpg?size=1167x781&quality=96&sign=cf0d1e93447c9be7bd0006a6f50a4e50&type=album) |
      
-----------------------------------------
            
Now the project is in its **final** state in terms of logic       
Actual problems:
- Performance issues on the AddDishScreen (because screen infinitely update image (I guess))
- Storing images in a database
- Lack of some CRUD operations on entities
- Very bad design
