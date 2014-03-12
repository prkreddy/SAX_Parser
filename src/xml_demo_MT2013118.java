import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class Customer
{

	private String customerId, companyName, contactTitle, phone, fax, address, city, region, postalCode, country;

	public String getCustomerId()
	{
		return customerId;
	}

	public void setCustomerId(String customerId)
	{
		this.customerId = customerId;
	}

	public String getCompanyName()
	{
		return companyName;
	}

	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}

	public String getContactTitle()
	{
		return contactTitle;
	}

	public void setContactTitle(String contactTitle)
	{
		this.contactTitle = contactTitle;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getFax()
	{
		return fax;
	}

	public void setFax(String fax)
	{
		this.fax = fax;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getRegion()
	{
		return region;
	}

	public void setRegion(String region)
	{
		this.region = region;
	}

	public String getPostalCode()
	{
		return postalCode;
	}

	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}
}

class Order
{

	String orderId, EmployeeId, OrderDate, RequiredDate;

	public String getOrderId()
	{
		return orderId;
	}

	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}

	public String getEmployeeId()
	{
		return EmployeeId;
	}

	public void setEmployeeId(String employeeId)
	{
		EmployeeId = employeeId;
	}

	public String getOrderDate()
	{
		return OrderDate;
	}

	public void setOrderDate(String orderDate)
	{
		OrderDate = orderDate;
	}

	public String getRequiredDate()
	{
		return RequiredDate;
	}

	public void setRequiredDate(String requiredDate)
	{
		RequiredDate = requiredDate;
	}

	public List<OrderItem> getOrderItems()
	{
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems)
	{
		this.orderItems = orderItems;
	}

	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}

	List<OrderItem> orderItems;

	Customer customer;
}

class OrderItem
{

	String product;

	public String getProduct()
	{
		return product;
	}

	public void setProduct(String product)
	{
		this.product = product;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	int quantity;
	float price;

}

interface Constants
{

	String WHITESPACE = "          ";
}

public class xml_demo_MT2013118 extends DefaultHandler
{

	Map<String, Customer> customers;
	Map<String, Order> orders;
	Customer customer;
	Order order;
	OrderItem orderItem;

	String elementData;

	public xml_demo_MT2013118()
	{
		customers = new HashMap<String, Customer>();
		orders = new HashMap<String, Order>();
	}

	void parseDocument(String filePath)
	{

		SAXParserFactory factory = SAXParserFactory.newInstance();
		try
		{
			SAXParser parser = factory.newSAXParser();
			try
			{
				parser.parse(filePath, this);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		catch (ParserConfigurationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SAXException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
	{

		if (qName.equalsIgnoreCase("Customer"))
		{
			customer = new Customer();
			customer.setCustomerId(attributes.getValue("CustomerID"));

		}
		else if (qName.equalsIgnoreCase("Order"))
		{
			order = new Order();
			order.setOrderId(attributes.getValue("OrderID"));
		}
		else if (qName.equals("OrderDetails"))
		{
			elementData = attributes.getValue("OrderID");
			order = orders.get(elementData);
			if (order != null)
				order.setOrderItems(new ArrayList<OrderItem>());
			else
			{
				order = new Order();
				order.setOrderId(elementData);
				order.setOrderItems(new ArrayList<OrderItem>());
			}
		}
		else if (qName.equalsIgnoreCase("OrderItem"))
		{
			orderItem = new OrderItem();
		}

	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException
	{
		elementData = new String(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException
	{

		if (qName.equalsIgnoreCase("CompanyName"))
		{
			customer.setCompanyName(elementData);
		}
		else if (qName.equalsIgnoreCase("ContactTitle"))
		{
			customer.setContactTitle(elementData);
		}
		else if (qName.equalsIgnoreCase("Address"))
		{
			customer.setAddress(elementData);
		}
		else if (qName.equalsIgnoreCase("City"))
		{
			customer.setCity(elementData);
		}
		else if (qName.equalsIgnoreCase("Region"))
		{
			customer.setRegion(elementData);
		}
		else if (qName.equalsIgnoreCase("PostalCode"))
		{
			customer.setPostalCode(elementData);
		}
		else if (qName.equalsIgnoreCase("Country"))
		{
			customer.setCountry(elementData);
		}
		else if (qName.equalsIgnoreCase("Fax"))
		{
			customer.setFax(elementData);

		}
		else if (qName.equalsIgnoreCase("Phone"))
		{
			customer.setPhone(elementData);

		}
		else if (qName.equalsIgnoreCase("Customer"))
		{
			customers.put(customer.getCustomerId(), customer);
		}
		else if (qName.equalsIgnoreCase("CustomerID"))
		{
			order.setCustomer(customers.get(elementData));
		}
		else if (qName.equalsIgnoreCase("EmployeeID"))
		{
			order.setEmployeeId(elementData);
		}
		else if (qName.equalsIgnoreCase("OrderDate"))
		{
			order.setOrderDate(elementData);
		}
		else if (qName.equalsIgnoreCase("RequiredDate"))
		{
			order.setRequiredDate(elementData);
		}
		else if (qName.equalsIgnoreCase("Order"))
		{
			orders.put(order.getOrderId(), order);
		}
		else if (qName.equalsIgnoreCase("Product"))
		{
			orderItem.setProduct(elementData);
		}
		else if (qName.equalsIgnoreCase("Quantity"))
		{
			orderItem.setQuantity(Integer.parseInt(elementData));
		}
		else if (qName.equalsIgnoreCase("Price"))
		{
			orderItem.setPrice(Float.parseFloat(elementData));
		}
		else if (qName.equalsIgnoreCase("OrderItem"))
		{
			order.getOrderItems().add(orderItem);
		}
		else if (qName.equalsIgnoreCase("Order") || qName.equalsIgnoreCase("OrderDetails"))
		{

			if (orders.get(order.getOrderId()) == null)
			{
				orders.put(order.getOrderId(), order);
			}
		}

	}

	String getItemData(String itemName, String input)
	{
		int whiteSpaceLength = itemName.length() + Constants.WHITESPACE.length() - input.length();
		String data = "";
		StringBuilder br = new StringBuilder();
		br.append(input);
		for (int i = 0; i < whiteSpaceLength; i++)
		{
			br.append(" ");
		}
		if (whiteSpaceLength > 0)
		{
			data = br.toString();
		}
		else
		{
			data = br.toString().substring(0, itemName.length() + Constants.WHITESPACE.length());
		}
		return data;

	}

	void generateInvoiceDocuments()
	{
		BufferedWriter writer = null;
		File file = null;
		StringBuilder br = null;
		for (String orderId : orders.keySet())
		{

			order = orders.get(orderId);
			if (order != null)
			{
				customer = order.getCustomer();
				br = new StringBuilder();
				file = new File(System.getProperty("user.dir") + "/invoice_" + order.getOrderId());
				try
				{
					writer = new BufferedWriter(new FileWriter(file));
					if (!file.exists())
					{

						file.createNewFile();

					}
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				br.append("To\n");

				if (customer != null)
				{
					// customerDetails

					br.append("\n" + customer.getContactTitle());
					br.append("\n" + customer.getCompanyName());
					br.append("\n" + customer.getAddress());

					br.append("\n" + customer.getCity());
					br.append("\n" + customer.getRegion() + "  " + customer.getPostalCode());
					br.append("\n" + customer.getCountry());
					if (customer.getPhone() != null)
						br.append("\nContact:  " + customer.getPhone());
					if (customer.getFax() != null)
						br.append("\nFax: " + customer.getFax());
				}
				else
				{

					br.append("\n\n no Customer Details provided \n\n");
				}

				if (order.getOrderDate() != null)
				{
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat simpleDate = new SimpleDateFormat("dd-MMM-yyyy");
					try
					{

						br.append("\n\nInvoice Date: "
								+ simpleDate.format(simpleDateFormat.parse(order.getOrderDate().split("T")[0])));
					}
					catch (ParseException ex)
					{
						System.out.println("Exception " + ex);
					}
				}

				br.append("\n\nOrder ID:" + order.getOrderId());

				br.append("\n\nS. No." + Constants.WHITESPACE + "Item" + Constants.WHITESPACE + "Qty"
						+ Constants.WHITESPACE + "Price" + Constants.WHITESPACE + "Amount");
				int len = ("S. No." + Constants.WHITESPACE + "Item" + Constants.WHITESPACE + "Qty"
						+ Constants.WHITESPACE + "Price" + Constants.WHITESPACE + "Amount").length();
				br.append("\n" + getSeparator(len, '-'));
				int count = 1;
				float sum = 0;
				float tempSum = 0;
				for (OrderItem orderItem : order.getOrderItems())
				{
					if (orderItem != null)
					{
						tempSum = orderItem.getPrice() * orderItem.getQuantity();

						br.append("\n" + getItemData("S. No.", count + "")
								+ getItemData("Item", orderItem.getProduct().toString())
								+ getItemData("Qty", orderItem.getQuantity() + "")
								+ getItemData("Price", orderItem.getPrice() + "") + tempSum);

						sum = sum + tempSum;
						++count;
					}
				}
				br.append("\n" + getSeparator(len, '-'));

				br.append("\n" + getItemData("S. No.", "Total") + getItemData("Item", "") + getItemData("Qty", "")
						+ getItemData("Price", "") + sum);
				br.append("\n" + getSeparator(len, '-'));
				try
				{
					writer.append(br);
					writer.flush();
					writer.close();
				}
				catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String args[])
	{
		xml_demo_MT2013118 xmlparser = new xml_demo_MT2013118();
		if (args.length > 0)
		{
			xmlparser.parseDocument(args[0]);
			xmlparser.generateInvoiceDocuments();
		}
		else
		{
			System.out.println("no filePath provided in arguments");
		}

	}

	public String getSeparator(int length, char delimeter)
	{

		StringBuilder br = new StringBuilder();
		for (int i = 0; i < length; i++)
		{
			br.append(delimeter);
		}
		return br.toString();
	}
}
