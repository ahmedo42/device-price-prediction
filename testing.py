import requests
import csv
import random

# Define the base URL of the Java API
base_url = 'http://localhost:8080'

def test_get_all_devices():
    # Make a GET request to the /api/devices endpoint
    response = requests.get(base_url + "/api/devices/")

    # Assert that the response status code is 200 (OK)
    assert response.status_code == 200

    # Assert that the response contains JSON data
    assert response.headers['Content-Type'] == 'application/json'

    # Parse the response JSON data
    devices = response.json()

    # Assert that the response contains at least one device
    assert len(devices) == 10

    print("GET /api/devices - Test passed successfully")

def test_get_device_by_id():
    # Choose an existing device ID for testing
    device_id = random.randint(1,10)

    # Make a GET request to the /api/devices/{id} endpoint
    response = requests.get(f'{base_url}/api/devices/{device_id}')

    # Assert that the response status code is 200 (OK)
    assert response.status_code == 200

    # Assert that the response contains JSON data
    assert response.headers['Content-Type'] == 'application/json'

    # Parse the response JSON data
    device = response.json()

    # Assert that the response device has the expected ID
    assert device['id'] == device_id

    print(f"GET /api/devices/{device_id} - Test passed successfully")

def test_add_devices():
    # Read devices from CSV file
    with open('data/test.csv', 'r') as file:
        reader = csv.DictReader(file)
        devices = list(reader)

    # Add each device from the CSV file to the API
    for i in range(10):
        device = devices[i]
        url = f"{base_url}/api/devices/"
        response = requests.post(url, json=device)

        # Assert that the response status code is 201 (Created)
        assert response.status_code == 201

    print("POST /api/devices - Added 10 devices from CSV file successfully")

def test_predict():
    # Choose a device ID for testing
    device_id = 1

    url = f'{base_url}/api/predict/{device_id}'
    # Make a POST request to the /api/predict/{deviceId} endpoint
    response = requests.post(url)    # Assert that the response status code is 200 (OK)
    assert response.status_code == 200


    # Parse the response JSON data
    prediction = response.json()["predicted_price_range"]

    # Assert that the prediction is within the expected range
    assert prediction in [0, 1, 2, 3]

def main():
    # Run the test cases
    test_add_devices()
    test_get_all_devices()
    test_get_device_by_id()
    test_predict()

if __name__ == "__main__":
    main()