This project comprises two main components:

### 1 . ml-service

This part includes an Exploratory Data Analysis (EDA) and training notebook. It trains the model on the mobile devices saves the trained model to disk Additionally, it also the trained model via a Flask endpoint.

### 2. web-service

The web-service is built on Spring Boot framework. It implements a simple model and a controller to manage devices, utilizing an in-memory H2 database to store device data. The backend provides the following endpoints:

- `POST /api/devices/`: Retrieves a list of all devices.
- `GET /api/devices/{id}`: Retrieves details of a specific device by ID.
- `POST /api/devices`: Adds a new device and saves it to the in-memory H2 database.
- `POST /api/predict/{deviceId}`: Endpoint for making predictions based on a device ID.

Additionally, a `testing.py` script is included to test all functionalities using data from CSV files.

How to Run the Project
```python
# Install ml-service dependencies:
pip install -r ml-service/requirements.txt

# Start ml-service:
python3 ml-service/deployment.py

# Build web-service:
# this assumes open-jdk-21 is installed and JAVA_HOME  env variable is set correctly 
./web-service/mvnw -f web-service/pom.xml clean package

# Start web-service:
./web-service/mvnw -f web-service/pom.xml spring-boot:run

# Run validation tests:
python3 testing.py
```

# Possible Improvements:
- Improve error handling in both services
- Robust input validation when recieving requests