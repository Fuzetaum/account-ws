@UpdateBalanceJob
Feature: UpdateBalanceJob
    Checks if the job for updating balances is correct when there are no operations to be processed

    Scenario: Update job with no operations to be computed
        Given the following accounts exist
        |    id     |  owner_name  | currency | balance | balance_last_update |
        | account-1 | Albert Allen |     6    |  42355  |      2018-05-23     |
        | account-2 | Bruce Banner |     6    |  20000  |      2018-05-23     |
        | account-3 | Charles Cove |     6    |  2572   |      2018-05-23     |
        And the following operations exist
        | id |  account_id  |   processing_date   | amount  | is_withdraw |
        |  1 |  account-1   | 2019-10-01 12:00:00 | -100000 |     true    |
        |  2 |  account-1   | 2019-10-01 13:00:00 |  50000  |    false    |
        |  3 |  account-1   | 2019-10-01 14:00:00 |  50000  |    false    |
        When UpdateBalanceJob runs
        Then system will report that no accounts were changed