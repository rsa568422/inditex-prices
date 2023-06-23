##### RESUMEN #####

Se implementa la solución a la prueba técnica descomponiendo el problema en dos componentes, inditex-prices-model con
las reglas de negocio e inditex-prices-api con la implementación de un microservicio con api REST en springboot.

Se aplican los principios SOLID, el clean code y los patrones de diseño ahí donde se detecta la posibilidad de hacerlo.
Siguiendo las recomendaciones del libro "Arquitectura Limpia" de Robert C. Martin, se trata de hacer un componente de
modelo de negocio lo más independiente posible de los detalles de implementación, primando el principio de inversión de
dependencia para hacer que sea el componente de modelo quien indique a los demás que va a necesitar para operar.

##### inditex-prices-model #####

Se extrae la lógica de modelo de negocio al módulo inditex-prices-model, estableciendo en la clase Price las reglas a
seguir para saber si un precio es aplicable en una determinada fecha y un método para comparar que nos sirva para
determinar el precio de máxima prioridad en caso de tener varios precios a aplicar. Se agregan dependencias a los
artefactos commons-lang3 y lombok como herramientas básicas para la implementación, al ofrecer utilidades
y etiquetas para reducir la cantidad de código a implementar. Se emplean las herramientas JUnit y Mockito
para el testing. Se generan tres clases de entidad junto con el repositorio para los precios:
 - Price (PriceRepository)
 - Brand
 - Product

Versión de Java: 11
Cobertura del código con las pruebas: 100%

PriceRepository
---------------
Se genera la interfaz PriceRepository con los siguientes métodos:
 - findByBrandIdAndProductIdAndDate: método mínimo a implementar para las clases herederas. Se espera que se devuelva
                                     el conjunto de precios que cumplen el criterio de búsqueda.
 - findApplicablePrice: método default que hace uso de findByBrandIdAndProductIdAndDate para obtener el precio aplicable.

##### inditex-prices-api #####

Se agrega el módulo inditex-prices-ipa para la implementación de una api REST con springboot. Se hace uso de lombok
y commons-lang3 como en los demás módulos, agregando una base de datos en memoria con h2, el testing con
spring-boot-starter-test, una capa de persistencia alternativa con spring-boot-starter-data-jpa y se agrega swagger
al proyecto para poder probar el endpoint manualmente mediante springfox-boot-starter. Se realizan pruebas unitarias
y de integración en los distintos componentes del módulo.

Para la persistencia implementada en este módulo con h2 y JpaRepository, se agregan clases DTO propias con un método
toModel que las transforma en clases de entidad equivalentes. Se aplica un patrón de diseño adapter en la clase
PriceH2RepositoryAdapter para implementar la interfaz PriceRepository empleando la interfaz PriceH2Repository.

Se agrega la clase de configuración RepositoryConfig en la que podemos establecer la persistencia a utilizar.

Versión de Java: 11
Cobertura del código con las pruebas: 78%

http://localhost:8080/swagger-ui/index.html