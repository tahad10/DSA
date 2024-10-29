CREATE DATABASE pe2_db_a1;
CREATE DATABASE IF NOT EXISTS pe2_db_a1;
USE pe2_db_a1;
CREATE TABLE IF NOT EXISTS todos (id INTEGER PRIMARY KEY, title VARCHAR(100), description VARCHAR(500));
INSERT INTO todos (id, title, description)
		VALUES (1, 'Dekorieren', 'Es ist nun endlich so weit! Mit dem 01. November wird es Zeit, zügig die Weihnachtsdekorationen auszupacken.'),
		(2, 'New todo', '(NULL)'),
		(3, 'Weitere Todos für die TodoAPI! eintragen', '(NULL)'),
		(4, 'Backen', 'Bald sollte ich Weihnachtsplaetzchen backen.'),
		(13, 'Die Attribute eines Todo-Objekts für die TodoAPI definieren', '(NULL)'),
		(42, 'Die Geschaeftslogik fuer die TodoAPI entwerfen', ' ');




















package de.unistuttgart.iste.pe2;

import de.unistuttgart.iste.pe2.examples.JDBCExamples;
import de.unistuttgart.iste.pe2.examples.ORMExamples;

public class Main {

	public static void main(String[] args) {

		JDBCExamples jdbcExamples = new JDBCExamples();
		jdbcExamples.runDemonstration();

		// ORMExamples ormExamples = new ORMExamples();
		// ormExamples.runDemonstration();
	}
}

