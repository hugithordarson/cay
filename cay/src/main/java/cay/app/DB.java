package cay.app;

import java.util.List;

import org.apache.cayenne.ObjectContext;
import org.apache.cayenne.query.ObjectSelect;
import org.apache.cayenne.runtime.CayenneRuntime;

import cay.data.Person;

public class DB {

	private static CayenneRuntime _runtime = createRuntime();

	private static CayenneRuntime createRuntime() {
		return CayenneRuntime
				.builder()
				.addConfig( "cayenne/cayenne-project.xml" )
				.build();
	}

	public static CayenneRuntime runtime() {
		return _runtime;
	}

	public static void main( String[] args ) {
		ObjectContext oc = runtime().newContext();

		Person p1 = oc.newObject( Person.class );
		p1.setName( "Hugi" );

		Person p2 = oc.newObject( Person.class );
		p2.setName( "Paul" );

		oc.commitChanges();

		List<Person> people = ObjectSelect
				.query( Person.class )
				.select( oc );

		for( Person person : people ) {
			System.out.println( person.getName() );
		}
	}
}