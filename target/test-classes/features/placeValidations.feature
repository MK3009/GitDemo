Feature: Validating Place API
@AddPlace
Scenario Outline: Verify if place is being added successfully using AddPlace API
	Given Add Place Payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call got success with Status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	And verify place_id created with "<name>" using "GetPlaceAPI"
	
	Examples:
	 | name | language | address | 
	 |Aashirvad home| English | World trade centre | 
#	 |Celebrity home| French | PVK | 

@DeletePlace
Scenario: Verify Delete place functionality is working
	Given DeletePlace payload
	When user calls "DeletePlaceAPI" with "POST" http request
	Then the API call got success with Status code 200
	And "status" in response body is "OK"