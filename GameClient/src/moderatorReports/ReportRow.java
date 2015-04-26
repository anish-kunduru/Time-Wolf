package moderatorReports;

import javafx.beans.property.SimpleIntegerProperty;

public class ReportRow {
	
	public SimpleIntegerProperty id = new SimpleIntegerProperty();
	
	public int getId(){
		return id.get();
	}

}
