package com.twu.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 * 
 */
public class OrderReceipt {
    private Order order;
    private static final double TAX_RATE=0.1;

    public OrderReceipt(Order order) {
        this.order = order;
	}

	public String printReceipt() {
		StringBuilder output = new StringBuilder();

		// print headers
		output.append("======Printing Orders======\n");


        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());

		// prints lineItems
		double totalSalesTax = 0d;
		double total = 0d;
		for (LineItem lineItem : order.getLineItems()) {
			output.append(lineItem.getDescription());
			output.append('\t');
			output.append(lineItem.getPrice());
			output.append('\t');
			output.append(lineItem.getQuantity());
			output.append('\t');
			output.append(lineItem.totalAmount());
			output.append('\n');

			// calculate sales tax @ rate of 10%
            double salesTax = lineItem.totalAmount() * TAX_RATE;
            totalSalesTax += salesTax;

            // calculate total amount of lineItem = price * quantity + 10 % sales tax
            total += lineItem.totalAmount() + salesTax;
		}

		output.append("Sales Tax").append('\t').append(totalSalesTax);

		output.append("Total Amount").append('\t').append(total);
		return output.toString();
	}

}