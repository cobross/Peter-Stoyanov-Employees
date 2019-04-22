package MyPackage;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Employe {
    private int EmpId;
    private int PrjId;
    private Date Datefrom;
    private Date DateTo;
    public Employe(String EmployeData) {
        int[] intervals={0,0,0};
        int index=0;
        for (int i = 0; i < EmployeData.length(); i++) {
            if (EmployeData.charAt(i) == ' '){
                intervals[index]+=i;
                index++;
            }
        }
        index=EmployeData.length() - 1;
        EmpId = ExtractId(EmployeData, 0, intervals[0] - 1);
        PrjId = ExtractId(EmployeData, intervals[0] + 1,
            intervals[1] - 1);
        Datefrom = ExtractDate(EmployeData, intervals[1] + 1, intervals[2]-1);
        if (EmployeData.charAt(index) != 'l' && EmployeData.charAt(index) != 'L'){    
            DateTo = ExtractDate(EmployeData, intervals[2] + 1, 0);
        }
        else{
            DateTo = new Date();
        }
    }
    public int ExtractId(String EmployeData,int from, int to){
        String devide = EmployeData.substring(from,to);
        int id=Integer.parseInt(devide);
        return id;
    }
    public Date ExtractDate(String EmployeData, int from, int  to){
    	String devide;        
    	String devideDates;
    	int[] dates = { 0, 0, 0 };
    	Date DT;
    	if(to==0) {
    		devide = EmployeData.substring(from);
    	}
    	else {
    		devide = EmployeData.substring(from,to);
    	}
        devideDates = devide.substring(0, 4);
        dates[0] = Integer.parseInt(devideDates)-1900;
        devideDates = devide.substring(5, 7);
        dates[1] = Integer.parseInt(devideDates)-1;
        devideDates = devide.substring(8);
        dates[2] = Integer.parseInt(devideDates);
        DT = new Date(dates[0], dates[1], dates[2]);
        return DT;
    }
    public long FromDateToDate(Employe employe){
        long diff=0;
        if (DateTo.compareTo(employe.Datefrom)<0 || employe.DateTo.compareTo(Datefrom)<0){
            return 0;
        }
        else{
            if (Datefrom.compareTo(employe.DateTo)>0){
                if (DateTo.compareTo(employe.DateTo)<0){
                	diff=Datefrom.getTime()-DateTo.getTime();
                	diff=TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
                }
                else{
                	diff=Datefrom.getTime()-employe.DateTo.getTime();
                	diff=TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
                }
            }
            else{
                if (DateTo.compareTo(employe.DateTo)<0){
                	diff=employe.Datefrom.getTime()-DateTo.getTime();
                	diff=TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
                }
                else{
                	diff=employe.Datefrom.getTime()-employe.DateTo.getTime();
                	diff=TimeUnit.DAYS.convert(diff,TimeUnit.MILLISECONDS);
                }
            }
        }
        return -diff-1;
    }
	public int getEmpId() {
		return EmpId;
	}
	public void setEmpId(int empId) {
		EmpId = empId;
	}
	public int getPrjId() {
		return PrjId;
	}
	public void setPrjId(int prjId) {
		PrjId = prjId;
	}
	public Date getDatefrom() {
		return Datefrom;
	}
	public void setDatefrom(Date datefrom) {
		Datefrom = datefrom;
	}
	public Date getDateTo() {
		return DateTo;
	}
	public void setDateTo(Date dateTo) {
		DateTo = dateTo;
	}
	@Override
	public String toString() {
		return "Employe [EmpId=" + EmpId + ", PrjId=" + PrjId + ", Datefrom=" + Datefrom + ", DateTo=" + DateTo + "]\n";
	}
}
