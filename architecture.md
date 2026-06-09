# Trading Platform Backend

## Overview

A Spring Boot based paper-trading platform that supports:

* User management
* Asset management
* Buy/Sell trading
* Portfolio tracking
* Wallet management
* Automation rules
* Basket management
* Basket automation
* Scheduled execution

---

## Architecture

User
↓
Wallet
↓
Trade Service
↓
Portfolio
↓
Automation
↓
Basket Automation

---

## Database Tables

### users

Stores platform users.

Fields:

* id
* username
* email
* password_hash

---

### assets

Stores tradable assets.

Fields:

* id
* symbol
* name
* market
* price

Examples:

* AAPL
* MSFT
* GOOGL

---

### wallets

Stores paper trading balances.

Fields:

* id
* user_id
* balance

---

### trades

Stores executed trades.

Fields:

* id
* user_id
* asset_id
* quantity
* price
* type
* timestamp

Types:

* BUY
* SELL

---

### automation_rules

Single-stock automation rules.

Example:

IF AAPL < 320
BUY 2

Fields:

* id
* user_id
* symbol
* condition_type
* threshold
* action_type
* quantity
* active

---

### baskets

User-created stock groups.

Example:

Tech Basket

Fields:

* id
* user_id
* name

---

### basket_assets

Assets belonging to a basket.

Fields:

* id
* basket_id
* asset_id
* condition_type
* threshold
* quantity
* active

Example:

AAPL LESS_THAN 300 BUY 2

MSFT LESS_THAN 450 BUY 1

GOOGL LESS_THAN 180 BUY 3

---

## Endpoints

### Assets

GET /asset

Returns all assets.

---

### Wallet

GET /wallet/{userId}

Returns wallet balance.

---

### Trades

POST /trade/buy

POST /trade/sell

GET /trade/transactions/{userId}

GET /trade/portfolio/{userId}

---

### Automation

POST /automation

Create rule.

GET /automation

List rules.

POST /automation/run

Execute rules immediately.

---

### Basket

POST /basket

Create basket.

POST /basket/{basketId}/asset

Add asset.

GET /basket/{basketId}

Get basket.

DELETE /basket/{basketId}/asset

Remove asset.

GET /basket/{basketId}/valuation

Basket valuation.

---

### Basket Automation

POST /basket-automation/{basketId}/run

Execute basket rules.

---

## Portfolio Calculation

Portfolio aggregates all BUY and SELL trades.

Outputs:

* Symbol
* Quantity
* Average Price
* Current Price
* Profit / Loss

---

## Current Project Status

Completed:

✓ Users

✓ Assets

✓ Wallet

✓ Paper Trading

✓ Buy

✓ Sell

✓ Transactions

✓ Portfolio

✓ Automation Rules

✓ Scheduler

✓ Basket Management

✓ Basket Valuation

✓ Basket Automation

Planned:

* Cooldown Rules
* Strategy Engine
* RSI Indicator
* EMA Indicator
* Backtesting
* JWT Authentication
* React Dashboard
* Broker Integrations
* WebSocket Live Prices
