# Devices API

API for managing devices (**Lights**, **Fans**, **AirConditioners**) built with **Java 21**, **Spring Boot 3**, **Spring Data JPA**, and **H2 in-memory**.

## Defaults

| Setting   | Value              |
|-----------|--------------------|
| Port      | **8081**           |
| Base Path | **`/device`**      |
| Database  | **H2 (in-memory)** |

---

## Run locally

```bash
# from the project root (where pom.xml lives)
mvn spring-boot:run

# App URL
# http://localhost:8081/device
Endpoints
Lights
Method	Path	Description
GET	/lights	List
POST	/lights	Create
PUT	/lights/{id}/toggle	Toggle ON/OFF
DELETE	/lights/{id}	Delete

Fans
Method	  Path	Description
GET	      /fans	                      List
POST	    /fans	                      Create
PATCH	    /fans/{id}/toggle	          Toggle ON/OFF
PATCH    	/fans/{id}/speed?value={s}	Update speed (turns OFF when s = 0)
DELETE	  /fans/{id}	                Delete

Air Conditioners
Method	Path	Description
GET	/air-conditioners	List
POST	/air-conditioners	Create
PATCH	/air-conditioners/{id}/toggle	Toggle ON/OFF
PATCH	/air-conditioners/{id}/thermostat?value={t}	Update thermostat (turns OFF when thermostat == thermoOffMode)
PATCH	/air-conditioners/{id}/thermoOffMode?value={t}	Update thermoOffMode
DELETE	/air-conditioners/{id}	Delete
