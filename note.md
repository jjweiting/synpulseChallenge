1. There are approximately one hundred thousand e-banking customers
2. each with a couple thousands of transactions per month.
3. Every e-banking client has one or more accounts in different currencies (e.g. GBP, EUR, CHF)
4. Stored in Kafka with the key being the transaction ID and the value the JSON representation of the transaction
5. The transaction API will send a JWT token containing the user’s unique identity key (e.g. P-0123456789)
6. The exchange rate on any given date is provided by an external API

1. Git x CircleCI
2. JAVA Spring
3. Swagger
4. K8s
5. testing
6. JWT

For simplicity reasons, consider a money account transaction composed of the following attributes:
- Unique identifier (e.g. 89d3o179-abcd-465b-o9ee-e2d5f6ofEld46)
- Amount with currency (eg GBP 100-, CHF 75)
- Account IBAN (eg. CH93-0000-0000-0000-0000-0)
    - https://github.com/arturmkrtchyan/iban4j
- Value date (e.g. 01-10-2020)
- Description (e.g. Online payment CHF)
    - https://wise.com/help/articles/2932338/guide-to-chf-transfers


DB
Users 
id(uuid), firstName, lastName, createTime

Accounts
id(uuid), IBAN, userId, currency, amount, valueDate, createTime

Transactions
id(uuid), payerIBAN, payeeIBAN, amount, createTime

User API
1. Create User 
    - body: name, phoneNumber

UserAccount API
1. Create Account by user info
    - body: userId, currency

Transaction API
1. Create Transaction