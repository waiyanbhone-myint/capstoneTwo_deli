# DELI-cious POS System 🥪

A console-based Point of Sale (POS) application for a customizable sandwich shop. This system allows users to create orders, customize sandwiches, add drinks and chips, and generate receipts.

---

## 🚀 Features

- **Custom Sandwich Creation**: Choose bread type, size, meat, cheese, and sauces.
- **Signature Sandwich Options**: Quick selection of popular sandwiches like BLT and Philly Cheese Steak.
- **Add-ons**: Include drinks and chips to orders.
- **Order Management**: View current orders, cancel items, and checkout.
- **Receipt Generation**: Save receipts with timestamps in the `receipts/` directory.
- **Logging**: Track user actions and system events in `logs/log.txt`.
- **Inventory Tracking**: Monitor the number of items sold using a `HashMap`.
- **Loyalty Points System**: Earn points based on purchase amounts.
- **Inventory Report**: View daily sales summaries from the home screen.

---

## 🛠 Technologies Used

- **Java 17**
- **Maven**: For project management and build automation.
- **AWS EC2**: Deploy and run the application on a virtual server.
- **AWS SES (Simple Email Service)**: Send receipts to customer emails (optional feature).

---

## 📁 Project Structure

```
capstoneTwo_deli/
├── logs/                   # Contains log files
├── receipts/               # Stores generated receipts
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── ps/
│                   ├── model/        # Product classes (Sandwich, Drink, Chip, etc.)
│                   ├── signature/    # Signature sandwich classes
│                   ├── ui/           # User interface and interaction
│                   └── util/         # Utility classes (Logger, FileManager, InventoryTracker)
├── .gitignore
├── delicious-pos.jar       # Executable JAR file
└── pom.xml                 # Maven configuration file
```

---

## 🧪 Getting Started

### Prerequisites

- Java 17 or higher
- Maven installed

### Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/waiyanbhone-myint/capstoneTwo_deli.git
   cd capstoneTwo_deli
   ```

2. **Build the project using Maven**:
   ```bash
   mvn clean package
   ```

3. **Run the application**:
   ```bash
   java -jar target/delicious-pos.jar
   ```

---

## ☁️ Deployment on AWS EC2

1. **Launch an EC2 instance** with Java installed.
2. **Transfer the JAR file** to the EC2 instance using SCP:
   ```bash
   scp target/delicious-pos.jar ec2-user@your-ec2-ip:/home/ec2-user/
   ```
3. **Connect to your EC2 instance**:
   ```bash
   ssh ec2-user@your-ec2-ip
   ```
4. **Run the application**:
   ```bash
   java -jar delicious-pos.jar
   ```

---

## 📊 Inventory Report

Access the inventory report from the home screen to view the number of each item sold during the session. This feature uses a `HashMap` to track item counts.

---

## 📄 License

This project is licensed under the MIT License.

---

## 🙌 Acknowledgments

Developed by Wai Yan Bhone Myint as part of the Capstone Project.
