# FOOD DELIVERY APP 🥞
This team of three hardworking girls has successfully developed an innovative food delivery app using Java. Their collaboration and dedication have resulted in a seamless, efficient, and user-friendly platform that is transforming the food delivery industry.

<div align="center">  
  <img width="500" alt="image" src="https://github.com/anacimpeanu/FoodDeliveryApp/assets/115561036/504bcce6-6cd4-45da-bcaf-ae5a63f0e124">
</div>

<details>
  <summary>
    <h2> Description </h2>
  </summary>

### Food Delivery App Description 🌮

      This food delivery app is designed to provide a seamless and efficient user experience. 
      It features an interactive menu that allows users to easily browse and select from a variety of food options. 
      With robust login and sign-up functionalities, users can quickly create accounts and manage their profiles.

      The app includes a dedicated environment for moderators, enabling them to manage content effectively. 
      Users can place orders effortlessly, and the app displays a comprehensive list of shops, 
      allowing users to view detailed information about each one. 
      The app reads data from CSV files, ensuring efficient data management.

      One of the standout features is the rating system, where users can rate shops and sort 
      them based on their ratings. This helps users make informed decisions about where to order from.
      Moderators have the ability to add or remove shops and products, ensuring the listings are always current and relevant.
                      
</details>
<details>
  <summary> 
     <h2> Task Requirements 🍕 </h2>
  </summary>
  
### Assignment Definition
            🍕 Select a system with at least 10 actions/queries on 8 types of objects.
            🍕 Implement the project in Java.
            🍕 Classes with private/protected attributes and methods.
            🍕 Abstract classes and interfaces with default behavior.
            🍕 Follow OOP principles with class interactions.
            🍕 Use at least 2 different collection interfaces with multiple implementations.
            🍕 Inheritance and polymorphism in collections.
            🍕 Include at least one service class exposing system operations.
            🍕 Main class calling service methods.
            🍕 Define custom Exceptions.
            🍕 Utilize Enums.

### Storage 
          🍉 Utilize CSV files or databases to store 4 types of objects.
          🍉 Generic singleton services for file reading/writing.
          🍉 Data automatically loaded from files at system startup.

### Auditing
        🍿 Create an auditing service logging actions to a CSV file.
        🍿 Log format: name_of_action,timestamp.
</details>

  <details>
  <summary> 
     <h3> Food Delivery Application Features 🍤 </h3>
  </summary>
    
### Interactive Menu Interface:
      🍤 Users can browse through a visually appealing and interactive menu 
          interface, showcasing a wide range of food options.

### User Authentication:
      🍤 Robust user authentication system allowing users to 
      securely log in or sign up for an account.
      
### Moderator Environment:

      🍤 A dedicated platform for moderators to manage various
      aspects of the application, including adding or 
      removing restaurants and products.

### Order Placement:
      🍤 Seamless order placement functionality, enabling users
      to effortlessly select items and place orders with 
      just a few clicks.
      
### Shop Listings:
    🍤 Comprehensive listings of available restaurants, 
    including detailed information such as cuisine type, 
    location, and operating hours.
    
### Specific Shop Listing:
    🍤 Detailed view of individual restaurants, providing 
    users with specific information about a particular 
    establishment, its menu, and ratings.
    
### CSV Data Integration:
    🍤 Integration of CSV files for efficient data management, 
    ensuring that restaurant and product information is accurately 
    maintained and updated.
    
### Rating System for Restaurants:
    🍤 Interactive rating system allowing users to rate and review 
    restaurants based on their dining experiences, providing 
    valuable feedback to other users.
    
### Sorting Shops by Rating:
    🍤 Functionality to sort restaurants based on user ratings, 
    helping users make informed decisions when selecting
    where to order from.

### Adding and Removing Restaurants:

    🍤 Moderators can easily add new restaurants to the platform
    or remove existing ones as needed, 
    ensuring that the app's restaurant database remains 
    current and relevant.
    
### Adding and Removing Products:

    🍤 Ability for moderators to add new food items to restaurant 
    menus or remove existing ones, giving them control 
    over the available offerings.

### Detailed Shop View:

    🍤In-depth view of each restaurant, showcasing not only its menu 
    but also other relevant details such as delivery options,
    minimum order requirements, and special offers.
 <summary> 
     <h3> Classes 🌮 </h3>

   <summary> 
     <h3> Menu 🌮 </h3>
  </summary>
  
<b>Menu</b>: Represents the overall menu of items available for order.

<b>RMenu</b>: Possibly represents a refined or specialized menu within the system, providing additional options or features.

   <summary> 
     <h3> Services 🌮 </h3>
  </summary>
  
<b>Application</b>
This singleton class represents the main application instance. It ensures that only one instance of the application exists at any given time.

<b>ReadCSV</b>
This singleton class handles the reading of data from CSV files. It ensures that only one instance is responsible for reading CSV files throughout the application.

<b>Services</b>
This singleton class provides various services required by the application. It ensures that these services are globally accessible and that only one instance of each service exists.

<b>Audit Services</b>
This singleton class manages auditing functionalities within the application. It ensures that all auditing operations are centralized and that only one instance of the audit service exists.

<b>WriteCSV</b>
This singleton class handles the writing of data to CSV files. It ensures that only one instance is responsible for writing CSV files throughout the application.

<b>Login</b>
This singleton class manages user authentication and login functionalities. It ensures that only one instance is responsible for handling user login operations, maintaining consistency and security.

<b>Package</b>: Food
<b>Beverage:</b> Represents beverages available in the food delivery system.

<b>Candy</b>: Represents candy items that can be ordered.

<b>Food</b>: Represents generic food items that are available for order.

<b>Product</b>: Represents individual products that can be ordered, which may include beverages, candy, or other types of food items.
<b>Package</b>: Shop

      CaffeShop: Represents a café or coffee shop.
      CandyShop: Represents a shop specializing in selling candy.
      Restaurant: Represents a restaurant.
      Shop: Represents a generic shop entity.
      
<b>Package</b>: User

      DeliveryGuy: Represents a delivery person or courier.
      Login: Manages user authentication and login functionalities.
      Owner: Represents the owner or manager of a shop or restaurant.
      User: Represents a generic user entity within the system.

</details>
  </summary>
  
  ### TEAM 
  
  [Ana - Maria Cimpeanu](https://github.com/anacimpeanu)
  
  [Nicoleta Carmen Iova](https://github.com/nicoletaiova25)
  
  [Artemis Constantina Tismanaru ( Tina )](https://github.com/ArtemisTismanaru)
  

<details>
  <summary>
<h2>📸 Console view menu 🧁 </h2>
  </summary>
<div align="center">  
<img width="300" alt="image" src="https://github.com/anacimpeanu/FoodDeliveryApp/assets/115561036/a8b9cc3b-a7c7-4cb8-b715-d0e4b6c4356c">
</div>
<div align="center">  
<img width="300" alt="image" src="https://github.com/anacimpeanu/FoodDeliveryApp/assets/115561036/c2074488-95b0-474b-8d0c-e9aa510c60b5">
</div>
<div align="center">  
<img width="500" alt="image" src="https://github.com/anacimpeanu/FoodDeliveryApp/assets/115561036/381b0511-b2c1-4f85-9197-d132ebb4c5ed">
</div>
</details>

### 📽 Video : [FOOD DELIVERY APP 🧁]
### 🖥 Code : [ The Code 🧁 ](https://github.com/anacimpeanu/FoodDeliveryApp/tree/main/FoodDeliveryApp)

