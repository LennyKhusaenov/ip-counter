# Getting Started

### Base logic

<p>Application finds count of unique ips in the file that is passed as a command line argument.
</p>
<p>Algorithm based on the idea that ip v4 addresses have a limited length, which equals to 32 bits.
</p>
<p>We can reserve array storage of integers on an application startup with length 2^27 (2^27 ints with 2^5 bits each, so 2^32 bits
total).
Required amount of memory 512 MB plus minor associated costs. Time complexity O(n). </p>
<p>Steps:

1. Read line from file
2. Parse long view of ip bytes
3. Calculate integer index of ip in array by dividing this view by 32
4. If we have no value at this position, store it and increment count

</p>

### Application start

Application start command:<br>
`mvnw spring-boot:run -Dspring-boot.run.arguments=--file.path={YOUR_FILE_PATH_HERE}`

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.7/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.7/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.7/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

