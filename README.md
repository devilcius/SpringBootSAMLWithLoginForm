# Spring Boot Sample SAML 2.0 Service Provider and Form Login

## Project description

This project is a sample implementation of a **SAML 2.0 Service Provider**, completely built on **Spring Framework**. 
It combines two types of authentication: through SAML or a database backed form login.


**SSOCircle** ([ssocircle.com](http://www.ssocircle.com/en/portfolio/publicidp/)) is used as public Identity Provider for test purpose. You need a (free) account to test it.

**Form login** uses an in-memory database. Credentials for admin user are: admin/1231456. And for _simple_ user: user/123456

### How to create key store files
Under `/src/main/resources/saml` directory, first create the jks file:

```bash
keytool -genkey -alias technoage -keyalg RSA -keystore samlKeystore.jks -keysize 2048 -storepass 5a4t-AA
```
Answer the questions, when asked 'Enter key password' just press return.

Then import the sso circle certificates with the provided script `update-sso-circle-certifcate.sh`

### How to create a service provider metada in sso circle
First create an account in https://idp.ssocircle.com/sso, then, under 'Manage Metadata>Add new Service Provider', fill the form. For 'SAML Metadata Information of your SP', run the application and enter the content of the XML returned by http://localhost:8080/saml/metadata. Finally update the key `technoage.saml.entity-id` in `application.properties` with the FQDN of the service provider your entered in the ssocircle.com form.

### References

#### Spring Boot

> Spring Boot makes it easy to create Spring-powered, production-grade applications and services with absolute minimum fuss.  It takes an opinionated view of the Spring platform so that new and existing users can quickly get to the bits they need.
> - **Ref.:** [http://projects.spring.io/spring-boot/](http://projects.spring.io/spring-boot/)

#### Spring Security SAML Extension

> Spring SAML Extension allows seamless inclusion of SAML 2.0 Service Provider capabilities in Spring applications. All products supporting SAML 2.0 in Identity Provider mode (e.g. ADFS 2.0, Shibboleth, OpenAM/OpenSSO, Ping Federate, Okta) can be used to connect with Spring SAML Extension.
> - **Ref.:** [http://projects.spring.io/spring-security-saml/](http://projects.spring.io/spring-security-saml/)

---------

### Run

`mvn clean package`

`mvn spring-boot:run`

---

Inspired in Vincenzo de Notaris' [project](http://github.com/vdenotaris/spring-boot-security-saml-sample).


