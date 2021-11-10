package automationFramework;

public class Objective 
{
	
public String objName;
public String description;
public String measure;
public String strategicGoal;
public String contributesTo;
public String startDate;
public String endDate;
public String nextReviewDate;
public String weight;
public boolean requiredForBonus;
public boolean isPrivate;
public String status;

public Objective ()
{
	
}

public Objective(String name, String desc, String meas, String goal, String contri, String start,
		String end, String review, String wt, boolean bonus, boolean priv)
{
	objName = name;
	description = desc;
	measure = meas;
	strategicGoal = goal;
	contributesTo = contri;
	startDate = start;
	endDate = end;
	nextReviewDate = review;
	weight = wt;
	requiredForBonus = bonus;
	isPrivate = priv;
}

public boolean equals(Object ob)
{
	  
    if (ob == this) {
        return true;
    }

    if (!(ob instanceof Objective)) {
        return false;
    }
     
    Objective obj = (Objective) ob;
     
    return objName.equals(obj.objName) && description.equals(obj.description) 
    		&& measure.equals(obj.measure) && startDate.equals(obj.startDate)
    		&& endDate.equals(obj.endDate) && nextReviewDate.equals(obj.nextReviewDate);
}
}
