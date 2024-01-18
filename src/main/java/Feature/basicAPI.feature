Feature: testing map api call

  @basicAPI
  Scenario: basic post call and validation
    Given User load the payload
    When User post the call
    Then User validate the status as 200
    And User validate the "scope" with "APP"


  @basicAPIwithExample
  Scenario Outline: basic post call and validation
    Given User load the payload with "<Name>" , "<Phone>" and "<Address>"
    When User post the call for "AddPlaceAPI"
    Then User validate the status as 200
    And User validate the "scope" with "APP"
    Examples:
      | Name    | Phone  | Address |
      | TestAPI | 576386 | MH,IN   |
      | Intell  | 345433 | IN      |

  @PostAPI @checkingvariable
  Scenario Outline: basic call and validation
    Given User load the payload with "<Name>" , "<Phone>" and "<Address>"
    When User "post" the call for "AddPlaceAPI"
    Then User validate the status as 200
    And User validate the "scope" with "APP"
    And User save the "place_id" from response
    Examples:
      | Name    | Phone  | Address |
      | TestAPI | 576386 | MH,IN   |
     # | Intell  | 345433 | IN      |

  @PutAPI @checkingvariable
  Scenario Outline: testing reusability
   # Given User "post" the payload with "<Name>" , "<Phone>" and "<Address>"
    Given User load the payload for "PUT"
    When User "put" the call for "UpdatePlaceAPI"
    Then User validate the status as 200
    And User validate the "msg" with "Address successfully updated"
    Examples:
      | Name    | Phone  | Address |
      | TestAPI | 576386 | MH,IN   |

  @GetAPI @checkingvariable
  Scenario Outline: testing reusability
   # Given User "post" the payload with "<Name>" , "<Phone>" and "<Address>"
    Given User load the payload for "GET"
    When User "get" the call for "GetPlaceAPI"
    Then User validate the status as 200
    #And User validate the "msg" with "Address successfully updated"
    Examples:
      | Name    | Phone  | Address |
      | TestAPI | 576386 | MH,IN   |