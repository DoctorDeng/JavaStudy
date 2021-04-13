package xml.entity;
/**
 * 书实体
 * @author Doctor邓
 *
 */
public class Book {
	//书 ID
	private String id;
	//书名
	private String name;
	//作者
	private String author;
	//出版年份
	private String year;
	//价格
	private String price;
	//书语言
	private String language;
	
	public Book() {
		super();
	}
	
	public Book(String id, String name, String author, String year, String price, String language) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.year = year;
		this.price = price;
		this.language = language;
	}

	public Book(String name, String author, String year, String price, String language) {
		super();
		this.name = name;
		this.author = author;
		this.year = year;
		this.price = price;
		this.language = language;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", year=" + year + ", price=" + price
				+ ", language=" + language + "]";
	}
}
