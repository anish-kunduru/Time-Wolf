package moderatorReports;

import javafx.beans.property.SimpleIntegerProperty;

public class ReportRow {
	
	public SimpleIntegerProperty id = new SimpleIntegerProperty();
	
	public int getID(){
		return id.get();
	}

}
