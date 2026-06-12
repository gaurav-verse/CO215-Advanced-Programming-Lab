products = []

n = int(input("Enter number of products: "))

for i in range(n):
    name = input(f"Enter product name {i + 1}: ")
    stock = float(input(f"Enter stock quantity for {name}: "))

    product = {
        "name": name,
        "stock": stock
    }

    products.append(product)

print("\nProducts with stock less than 10:")

found = False
for product in products:
    if product["stock"] < 10:
        print(f"Product: {product['name']}, Stock: {product['stock']}")
        found = True

if not found:
    print("No products with stock less than 10.")
