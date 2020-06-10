![Clojure CI](https://github.com/self-tuts/email-microservice/workflows/Clojure%20CI/badge.svg)

# Email Microservice

This is a email microservice that exposes rest api to send email.




# REST API

Collection of REST API exposed by the email service

## Send Email

### Request 

```bash
    POST http://localhost:3000/email
```

### Body

```bash
    {
       "to"       : "<email-address>"          // mandatory
       "subject"  : "<subject-of-email>"       // mandatory
       "message"  : "<message-of-email>"       // mandatory
    }
```

### Response 200

```bash
    {
        "success" : true
    } 
```