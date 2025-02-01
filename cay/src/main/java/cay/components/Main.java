package cay.components;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;

import cay.app.DB;
import cay.data.Person;

public class Main extends WOComponent {

	public String newPersonName;

	public Person person;

	private static final long serialVersionUID = 1L;

	public Main( WOContext context ) {
		super( context );
	}

	public WOActionResults createPerson() {
		ObjectContext oc = DB.runtime().newContext();

		Person newPerson = oc.newObject( Person.class );
		newPerson.setName( newPersonName );

		oc.commitChanges();

		return null;
	}

	public List<Person> people() {
		return ObjectSelect
				.query( Person.class )
				.orderBy( Person.NAME.asc() )
				.select( DB.runtime().newContext() );
	}
}
