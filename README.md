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
<br />
Through the above diagram, It is possible to map each entity into a _Java_ Class. The entity's attributes correspond to the state of the object. The figure 2 reflects the mentioned mapping.

<div align="center">

| Entity   | Class         |
|----------|---------------|
| Employee | Employee.java |
| User     | User.java     |
| Vehicle  | Vehicle.java  |

</div>
<div align = "center">Figure 2 - Entity-to-Class table.</div>
