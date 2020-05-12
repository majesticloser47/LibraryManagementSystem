package application;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class SampleController implements Initializable{
	@FXML
	private ImageView close,about,closedetails,closeabout,adduser,closeadduser,addbook,closeaddbook,showmem,closeshowmem,showbooks,closeshowbooks,borrow,closeborrow, ret,closeret,fine,closefine;
	@FXML private TextField returnisbn=new TextField(), borrowisbn=new TextField(), borrowmemid=new TextField(), aut1=new TextField(),aut2=new TextField(),aut3=new TextField(),aut4=new TextField(),aut5=new TextField(),searchmember=new TextField(), booksearch=new TextField(),aub1=new TextField(),aub2=new TextField(),aub3=new TextField(),aub4=new TextField(),aub5=new TextField();
	@FXML private TableView<Member> memtable;
	@FXML private TableView<Book> booktable;
	@FXML private TableColumn<Member, String> memidCol;
	@FXML private TableColumn<Member, String> firstnameCol;
	@FXML private TableColumn<Member, String> lastnameCol;
	@FXML private TableColumn<Member, Integer> ageCol;
	@FXML private TableColumn<Member, String> typeCol;
	@FXML private TableColumn<Book, String> isbnCol;
	@FXML private TableColumn<Book, String> titleCol;
	@FXML private TableColumn<Book, String> genreCol;
	@FXML private TableColumn<Book, String> autCol;
	@FXML private TableColumn<Book, String> pubcodeCol;
	@FXML private TableColumn<Book, String> statCol;
	@FXML
	private AnchorPane aboutpane,detailspane, mainPane,adduserpane,addbookpane,showmemberspane,showbookspane,borrowpane,retpane,closepane, finepane;
	private ObservableList<String> l1=FXCollections.observableArrayList("Select","Member","Librarian");
	private ObservableList<String> l2=FXCollections.observableArrayList("Select","Member ID","First Name","Last Name","Age","Member type");
	private ObservableList<Member> l3=FXCollections.observableArrayList();
	private ObservableList<Member> l4=FXCollections.observableArrayList();
	private ObservableList<Book> l5=FXCollections.observableArrayList();
	private ObservableList<Book> l6=FXCollections.observableArrayList();
	private ObservableList<String> l7=FXCollections.observableArrayList("Select","ISBN","Title","Genre","Author","Publisher Code","Status");
	@FXML private TextArea details=new TextArea("");
	@FXML
	private ComboBox usertype,searchtype,booksearchtype;
	@FXML
	private Button returnsubmit,returnreset,adduserreset,displaybookdetails, borrowsubmit, borrowreset, addusersubmit,deleteuser,search,displaybook,deletebook,addbooksubmit, addbookreset;
	ExecuteQuery x=new ExecuteQuery();
	@FXML
	public void initializeAddUser()
	{
		aut1.setText("");
		aut2.setText("");
		try {
			ResultSet r=x.query("select count(*) as \"n\" from Members");
			while(r.next())
			aut3.setText(""+r.getInt("n"));
			r.close();
		}catch(Exception f) {System.out.println("number display exception: "+f);}
		
		aut4.setText("");
		aut5.setText("");
		usertype.setItems(l1);
		usertype.getSelectionModel().select(0);
	}
	@FXML
	public void initializeShowMembers()
	{
		searchmember.setText("");
		//searchmember.setText("");
		searchtype.setItems(l2);
		memtable.setItems(FXCollections.observableArrayList());
		l3.clear();
		searchtype.getSelectionModel().select(0);
		try {
			//ExecuteQuery x=new ExecuteQuery();
			ResultSet r=x.query("select * from Members order by memid");
			while(r.next())
			{
				l3.add(new Member(r.getString("memid"), r.getString("firstname"),r.getString("lastname"),r.getInt("age"),r.getString("type")));
			}
			memtable.setItems(l3);
			r.close();
		}
		catch(Exception e)
		{
			System.out.println("Udhar ka "+e);
		}
		memidCol.setCellValueFactory(new PropertyValueFactory<>("memid"));
		firstnameCol.setCellValueFactory(new PropertyValueFactory<>("firstname"));
		lastnameCol.setCellValueFactory(new PropertyValueFactory<>("lastname"));
		ageCol.setCellValueFactory(new PropertyValueFactory<>("age"));
		typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
	}
	@FXML
	public void initializeShowBooks()
	{
		booktable.setItems(FXCollections.observableArrayList());
		l5.clear();
		booksearch.setText("");
		booksearchtype.setItems(l7);
		booksearchtype.getSelectionModel().select(0);
		try {
			//ExecuteQuery x=new ExecuteQuery();
			ResultSet r=x.query("select * from books");
			while(r.next())
			{
				l5.add(new Book(r.getString("isbn"), r.getString("title"),r.getString("genre"),r.getString("author"),r.getString("pubcode"), r.getString("status")));
			}
			booktable.setItems(l5);
			r.close();
		}
		catch(Exception e)
		{
			System.out.println(" ka "+e);
		}
		isbnCol.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
		genreCol.setCellValueFactory(new PropertyValueFactory<>("genre"));
		autCol.setCellValueFactory(new PropertyValueFactory<>("author"));
		pubcodeCol.setCellValueFactory(new PropertyValueFactory<>("pubcode"));
		statCol.setCellValueFactory(new PropertyValueFactory<>("status"));
	}
	public void initializeBorrow()
	{
		borrowisbn.setText("");
		borrowmemid.setText("");
	}
	@FXML
	public void initializeAddBook()
	{
		aub1.setText("");
		aub2.setText("");
		aub3.setText("");
		aub4.setText("");
		aub5.setText("");
		//usertype.setItems(l1);
		//usertype.getSelectionModel().select(0);
	}
	@FXML
	public void initializeReturn()
	{
		returnisbn.setText("");
	}
	@FXML
	private void handleButtonAction(MouseEvent e)
	{
		if(e.getTarget()==close)
			System.exit(0);
		else if(e.getTarget()==adduser)
		{
			mainPane.setVisible(false);
			adduserpane.setVisible(true);
			showmemberspane.setVisible(false);
			showbookspane.setVisible(false);
			addbookpane.setVisible(false);
			finepane.setVisible(false);
			aboutpane.setVisible(false);
			borrowpane.setVisible(false);
			retpane.setVisible(false);
			initializeAddUser();
		}
		else if(e.getTarget()==addusersubmit)
		{
			try {
				String typ="";
				if(usertype.getValue().toString().contentEquals("Librarian"))
					typ="L";
				else if(usertype.getValue().toString().contentEquals("Member"))
					typ="M";
				else
					System.out.println("Wrong type input");
				x.update("insert into Members values(\""+aut4.getText()+"\",\""+aut1.getText()+"\",\""+aut2.getText()+"\","+aut5.getText()+",\""+typ+"\")");
				//r.close();
				//con.close();
			}
			catch(Exception sql1)
			{
				System.out.println("Udhar ka "+sql1);
			}
			initializeAddUser();
		}
		else if(e.getTarget()==closeadduser)
		{
			adduserpane.setVisible(false);
			mainPane.setVisible(true);
		}
		else if(e.getTarget()==showmem)
		{
			mainPane.setVisible(false);
			showmemberspane.setVisible(true);
			showbookspane.setVisible(false);
			addbookpane.setVisible(false);
			finepane.setVisible(false);
			aboutpane.setVisible(false);
			adduserpane.setVisible(false);
			borrowpane.setVisible(false);
			retpane.setVisible(false);
			memtable.getItems().clear();
			initializeShowMembers();
		}
		else if(e.getTarget()==deleteuser)
		{	
			try {
				x.update("delete from Members where memid=\""+memtable.getSelectionModel().getSelectedItem().getMemid()+"\"");
				memtable.setItems(FXCollections.observableArrayList());
				l3.clear();
				ResultSet r=x.query("select * from Members order by memid");
				
				while(r.next())
				{
					l3.add(new Member(r.getString("memid"), r.getString("firstname"),r.getString("lastname"),r.getInt("age"),r.getString("type")));
				}
				
				memtable.setItems(l3);
				r.close();
			}
			catch(Exception f)
			{
				System.out.println("Search wala "+f);
			}
		}
		else if(e.getTarget()==borrowsubmit)
		{
			ResultSet r=x.query("select * from Books where isbn=\""+borrowisbn.getText()+"\"");
			try {
				r.next();
				x.update("insert into Borrowed values(\""+r.getString("isbn")+"\",\""+r.getString("title")+"\",\""+r.getString("genre")+"\",\""+r.getString("author")+"\",\""+r.getString("pubcode")+"\",\""+borrowmemid.getText()+"\")");
				x.update("update Books set status=\"B\" where isbn=\""+r.getString("isbn")+"\"");
			} catch (SQLException e1) {
				System.out.println(e1);
			}
			initializeBorrow();
		}
		else if(e.getTarget()==borrowreset)
		{
			initializeBorrow();
		}
		else if(e.getTarget()==returnsubmit)
		{
			ResultSet r=x.query("select * from Borrowed where isbn=\""+returnisbn.getText()+"\"");
			try {
				r.next();
				//x.update("insert into Books values(\""+r.getString("isbn")+"\",\""+r.getString("title")+"\",\""+r.getString("genre")+"\",\""+r.getString("author")+"\",\""+r.getString("pubcode")+"\",\"R\")");
				x.update("update Books set status=\"R\" where isbn=\""+r.getString("isbn")+"\"");
				x.update("delete from Borrowed where isbn=\""+r.getString("isbn")+"\"");
			} catch (SQLException e1) {
				System.out.println(e1);
			}
			initializeReturn();
		}
		else if(e.getTarget()==closeshowmem)
		{
			showmemberspane.setVisible(false);
			mainPane.setVisible(true);
		}
		else if(e.getTarget()==returnreset)
		{
			returnisbn.setText("");
		}
		else if(e.getTarget()==showbooks)
		{
			mainPane.setVisible(false);
			showbookspane.setVisible(true);
			addbookpane.setVisible(false);
			finepane.setVisible(false);
			aboutpane.setVisible(false);
			adduserpane.setVisible(false);
			showmemberspane.setVisible(false);
			borrowpane.setVisible(false);
			retpane.setVisible(false);
			initializeShowBooks();
		}
		else if(e.getTarget()==displaybookdetails)
		{
			details.setText("");  
			detailspane.setVisible(true);
			ResultSet r=x.query("select * from Books, Publishers where Books.isbn=\""+booktable.getSelectionModel().getSelectedItem().getIsbn()+"\" and Books.pubcode=Publishers.pubcode");
			try {
				r.next();
				details.appendText("Book details\n");
				details.appendText("----------------------------------------------------\n");
				details.appendText("Book Title: "+r.getString("title")+"\n");
				details.appendText("ISBN: "+r.getString("isbn")+"\n");
				details.appendText("Genre: "+r.getString("genre")+"\n");
				details.appendText("Author: "+r.getString("author")+"\n");
				details.appendText("Status: "+((r.getString("status").contentEquals("R"))?"Returned":"Borrowed")+"\n\n");
				details.appendText("Publisher details\n");
				details.appendText("----------------------------------------------------\n");
				details.appendText("Publisher Name: "+r.getString("name")+"\n");
				details.appendText("Publisher code: "+r.getString("pubcode")+"\n");
				details.appendText("Publisher Address: "+r.getString("address")+"\n");
				details.setEditable(false);
			} catch (SQLException e1) {
				System.out.println(e1);
			}
		}
		else if(e.getTarget()==closedetails)
		{
			details.setText("");
			detailspane.setVisible(false);
		}
		else if(e.getTarget()==deletebook)
		{
			try {
				x.update("delete from Books where isbn=\""+booktable.getSelectionModel().getSelectedItem().getIsbn()+"\"");
				booktable.setItems(FXCollections.observableArrayList());
				l5.clear();
				ResultSet r=x.query("select * from Books");
				
				while(r.next())
				{
					l5.add(new Book(r.getString("isbn"), r.getString("title"),r.getString("genre"),r.getString("author"),r.getString("pubcode"), r.getString("status")));
				}
				booktable.setItems(l5);
				r.close();
			} catch (Exception e1) {
				System.out.println(e);
			}
		}
		
		else if(e.getTarget()==closeshowbooks)
		{
			showbookspane.setVisible(false);
			mainPane.setVisible(true);
		}
		else if(e.getTarget()==addbook)
		{
			mainPane.setVisible(false);
			addbookpane.setVisible(true);
			finepane.setVisible(false);
			aboutpane.setVisible(false);
			adduserpane.setVisible(false);
			showmemberspane.setVisible(false);
			showbookspane.setVisible(false);
			borrowpane.setVisible(false);
			retpane.setVisible(false);
		}
		else if(e.getTarget()==addbooksubmit)
		{
			try {
				x.update("insert into Books values(\""+aub2.getText()+"\",\""+aub1.getText()+"\",\""+aub3.getText()+"\",\""+aub4.getText()+"\",\""+aub5.getText()+"\",\"R\")");
			}
			catch(Exception sql1)
			{
				System.out.println("Udhar ka "+sql1);
			}
			initializeAddBook();
		}
		else if(e.getTarget()==addbookreset)
		{
			initializeAddBook();
		}
		else if(e.getTarget()==closeaddbook)
		{
			addbookpane.setVisible(false);
			mainPane.setVisible(true);
		}
		else if(e.getTarget()==fine)
		{
			mainPane.setVisible(false);
			finepane.setVisible(true);
			aboutpane.setVisible(false);
			adduserpane.setVisible(false);
			showmemberspane.setVisible(false);
			showbookspane.setVisible(false);
			addbookpane.setVisible(false);
			borrowpane.setVisible(false);
			retpane.setVisible(false);
		}
		else if(e.getTarget()==closefine)
		{
			finepane.setVisible(false);
			mainPane.setVisible(true);
		}
		else if(e.getTarget()==about)
		{
			mainPane.setVisible(false);
			aboutpane.setVisible(true);
			adduserpane.setVisible(false);
			showmemberspane.setVisible(false);
			showbookspane.setVisible(false);
			addbookpane.setVisible(false);
			finepane.setVisible(false);
			borrowpane.setVisible(false);
			retpane.setVisible(false);
		}
		else if(e.getTarget()==closeabout)
		{
			aboutpane.setVisible(false);
			mainPane.setVisible(true);
		}
		else if(e.getTarget()==borrow)
		{
			mainPane.setVisible(false);
			aboutpane.setVisible(false);
			adduserpane.setVisible(false);
			showmemberspane.setVisible(false);
			showbookspane.setVisible(false);
			addbookpane.setVisible(false);
			finepane.setVisible(false);
			borrowpane.setVisible(true);
			retpane.setVisible(false);
			initializeBorrow();
		}
		else if(e.getTarget()==closeborrow)
		{
			borrowpane.setVisible(false);
			mainPane.setVisible(true);
		}
		else if(e.getTarget()==ret)
		{
			mainPane.setVisible(false);
			aboutpane.setVisible(false);
			adduserpane.setVisible(false);
			showmemberspane.setVisible(false);
			showbookspane.setVisible(false);
			addbookpane.setVisible(false);
			finepane.setVisible(false);
			borrowpane.setVisible(false);
			retpane.setVisible(true);
		}
		else if(e.getTarget()==closeret)
		{
			retpane.setVisible(false);
			mainPane.setVisible(true);
		}
	}
	@FXML
	public void SearchMember()
	{
			String s=searchmember.getText();
			
			memtable.setItems(FXCollections.observableArrayList());
			ResultSet r=null;
			try {
				
				if(searchtype.getValue().toString().contentEquals("Member ID") && s!="")
				{
					try {
						r=x.query("select * from Members where memid like \""+s+"%\" order by memid");
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				else if(searchtype.getValue().toString().contentEquals("First Name") && s!="")
				{
					try {
						r=x.query("select * from Members where firstname like \""+s+"%\" order by memid");
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				else if(searchtype.getValue().toString().contentEquals("Last Name") && s!="")
				{
					try {
						r=x.query("select * from Members where lastname like \""+s+"%\"order by memid");
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				else if(searchtype.getValue().toString().contentEquals("Age") && s!="")
				{
					try {
						r=x.query("select * from Members where age like \""+s+"%\" order by memid");
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				else if(searchtype.getValue().toString().contentEquals("Member type") && s!="")
				{
					try {
						r=x.query("select * from Members where type like \""+s+"%\" order by memid");
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				else
				{
					try {
						r=x.query("select * from Members order by memid");
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				l4.clear();
				//memtable.setItems(FXCollections.observableArrayList());
				while(r.next())
				{
					l4.add(new Member(r.getString("memid"), r.getString("firstname"),r.getString("lastname"),r.getInt("age"),r.getString("type")));
				}
				memtable.setItems(l4);
				r.close();
			}
			catch(Exception e)
			{
				System.out.println("idhar "+e);
			}
	}
	@FXML
	public void SearchBook()
	{
			String s=booksearch.getText();
			
			booktable.setItems(FXCollections.observableArrayList());
			ResultSet r=null;
			try {
				
				if(booksearchtype.getValue().toString().contentEquals("ISBN") && s!="")
				{
					try {
						r=x.query("select * from Books where isbn like \""+s+"%\"");
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				else if(booksearchtype.getValue().toString().contentEquals("Title") && s!="")
				{
					try {
						r=x.query("select * from Books where title like \""+s+"%\"");
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				else if(booksearchtype.getValue().toString().contentEquals("Genre") && s!="")
				{
					try {
						r=x.query("select * from Books where genre like \""+s+"%\"");
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				else if(booksearchtype.getValue().toString().contentEquals("Author") && s!="")
				{
					try {
						r=x.query("select * from Books where author like \""+s+"%\"");
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				else if(booksearchtype.getValue().toString().contentEquals("Publisher Code") && s!="")
				{
					try {
						r=x.query("select * from Books where pubcode like \""+s+"%\"");
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				else if(booksearchtype.getValue().toString().contentEquals("Status") && s!="")
				{
					try {
						r=x.query("select * from Books where status like \""+s+"%\"");
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				else
				{
					try {
						r=x.query("select * from Books");
					}
					catch(Exception e)
					{
						System.out.println(e);
					}
				}
				l6.clear();
				//memtable.setItems(FXCollections.observableArrayList());
				while(r.next())
				{
					l6.add(new Book(r.getString("isbn"), r.getString("title"),r.getString("genre"),r.getString("author"),r.getString("pubcode"), r.getString("status")));
				}
				booktable.setItems(l6);
				r.close();
			}
			catch(Exception e)
			{
				System.out.println("idhar "+e);
			}
	}

 	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initializeAddUser();
		initializeAddBook();
		initializeShowMembers();
		initializeShowBooks();
		initializeBorrow();
		initializeReturn();
		detailspane.setVisible(false);
	}	
}
