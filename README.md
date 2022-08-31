# Car Store 🚗

An application that displays the content of a certain car store, such as cars and its employees. The application consists on a local web service, which is equipped with different templates. According to the user's actions which template performs a different action/request.

## Authors

- [@joseluisgomes](https://www.github.com/joseluisgomes)

## Stages of the project

- Entity-Relationship diagram
- Translation of the diagram to code
- Construction of a **REST API**
- Development of **Web Service**
- Test-driven development

## Development of the project

The figure 1 shows the entity-relationship diagram of this project. From the analysis of this figure, We can conclude that this application relies on 4 entities: _Employee, User, Vehicle_ and _Authority_.

<p align="center">
    <img src="https://user-images.githubusercontent.com/70901488/187467998-703b5d8d-23b2-4fd2-89f1-26ccbc16f65d.png">
</p>
<div align = "center">Figure 1 - Entity Relationship Diagram.</div>

Through the above diagram, It is possible to map each entity into a _Java_ Class. The entity's attributes correspond to the state of the object. The figure 2 reflects the mentioned mapping.

<div align="center">

|   Entity   |       Class       |
|:----------:|:-----------------:|
|  Employee  |  `Employee.java`  |
|    User    |    `User.java`    |
|  Vehicle   |  `Vehicle.java`   |

</div>
<div align = "center">Figure 2 - Entity-to-Class table.</div>

Still inside the entity's discussion, some attributes need a special, an adequate treatment such as _Role, Fuel_ and _Motor_, which are attributes from _Employee_ and _Vehicle_ entities. Basically, the well suited solution is converting the mentioned attributes into _Java_ Enums.
<br />
The figure 3 demonstrates the connection between the cited entities and their attributes.

<p align="center">
    <img src="https://user-images.githubusercontent.com/70901488/187542260-161fd005-1cd2-4aa4-89e0-12dbb40d7559.png">
</p>
<div align = "center">Figure 3 - Class Diagram.</div>

## The heartwood of the Website

As mentioned in the index, the web service/website performs different tasks according to the user's operations. The website is equipped with a login page, only available to the users registered on the database.
<br />
The figure 4 shows the website's login page.

<p align="center">
    <img src="https://user-images.githubusercontent.com/70901488/187778578-66f8ba9e-08ce-4be4-a684-691d7e9b7c4d.png">
</p>
<div align = "center">Figure 4 - Login page.</div>
