# warehouse-springcloud
This system is about warehouse management and using spring cloud.

Introduce
This system is divided into 13 parts.

**1.admin-server**  
**2.auth-user**  
**3.auth-service**  
**4.commons-dao**  
**5.config-server**  
**6.eureka-server**  
**7.feign-user**  
**8.maiterial-service**  
**9.order-service**  
**10.product-service**  
**11.user-service**  
**12.utils**  
**13.zuul-server**  
-------

The admin-server is a monitor.It can see all the system health status.

The auth-service is about the security system part.It using the OAuth2 and JWT to protect the system.

The commons-dao is a base dao interface.It using to query data from the mysql.The system is not use the distribute transation,so we just using the common part to all the system where need to query from the databases.

The eureka-server is a register center.

The feign-user is a web site.Using the feign to call other part of system.And getting the data from the other part of the system, and show it one the website.








-----
git operation:
git checkout -b dev
-b mean create and switch the branch.

It also can use:
git branch dev
git checkout dev

show current branch:
git branch
-----