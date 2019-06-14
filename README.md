# SMS-Survey-Service

Survey API for SMS

## Group Members

* Lori Oliver
* Iman Davis-Young
* John Goncalves
* Kyle Serrecchia
* Scott Tucker
* Dunieski Otano
* Hank Zimmer
* Joseph Sperduto
* Ric Davis
* Ghiberty J. Meza
* Karmanya Suryavanshi
* Shreyas Kathavate

## Description

The Survey Service is part of a larger service, the Staging Management Service (SMS). The Survey Service API allows a Revature staging manager to quickly generate a survey with custom questions and send the survey to a group of associates. Users who receive the survey are able to click a link and take the survey. The survey creator can then view the responses and is able to view charts and graphs of the data and gain insight into the preferences of the group.

There are different fields types that the survey creator can use for their inputs, including text, numbers, and dates. The survey questions and answers are customizable. There are different options for viewing the data, such as in pie charts. The goal of the data viewing is to allow the survey creator to gain insight into the preferences of the target group.

# Environment Variables
url for interracting with cognito endpoints  
```COGNITO_URL: ask blake```  

key required for certain cognito endpoints  
```COGNITO_KEY: ask blake```

Password to access the database  
```DB_PASSWORD: password```

Schema for the database  
```DB_SCHEMA: user_service```

Url pointing to the database that the service will use  
```DB_URL: jdbc:postgresql://localhost:5432/postgres```

Username to log into the database   
```DB_USERNAME: username```

The default gateway can be changed in the application.yml file.
Please set this stage to prod for production or dev for development mode. Setting to dev will void auth requirements.  
```DEPLOYMENT_STAGE: dev```

### The below environment variables are only needed for production
Gateway URL  
```GATEWAY_URL: ask blake if needed```