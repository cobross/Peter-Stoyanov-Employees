using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace MyProject
{
    public partial class Form1 : Form
    {
        // EmployeData a string for reading file lines. 
        //Pairs is a list of two employees
        //and their time working together in days.
        //Employes is a list of all employees.
        string EmployeData;
        List<PaiR> pairs = new List<PaiR>();
        List<Employe> Employes = new List<Employe>();
        public Form1()
        {
            InitializeComponent();          
        }
        //This function finds the pair with the longest worktime.
        public void findPair()
        {
            int pos = 0;
            for (int i = 0; i < Employes.Count; i++)
            {
                for (int j = 0; j < Employes.Count; j++)
                {
                    if(i!=j)
                    {
                        if (Employes[i].PrjId == Employes[j].PrjId)
                        {
                            pos=check(Employes[i].EmpId,Employes[j].EmpId);
                            if (0==pos)
                            {

                                pairs.Add(new PaiR(Employes[i].EmpId,Employes[j].EmpId,
                                Employes[i].FromDateToDate(Employes[j])));    
                            }
                            else
                            { 
                               pairs[pos].plus(Employes[i].FromDateToDate(Employes[j]));
                            }
     
                        }
                    }
                }
            }
            pos = find();
            MessageBox.Show("first employe id - "+pairs[pos].id1.ToString()
                + "\nsecond employe id - " + pairs[pos].id2.ToString()+
                "\ntimeSpent working together - " + pairs[pos].timeSpent.ToString());
        }

        private void button1_Click(object sender, EventArgs e)
        {
            System.IO.StreamReader FileReader = new System.IO.
    StreamReader(textBox1.Text+".txt");
            while ((EmployeData = FileReader.ReadLine()) != null)
            {
                Employes.Add(new Employe(EmployeData));
            }
            findPair();
        }
        //This function checks to see if we have 
        //already added a pair to the list.
        public int check(int a, int b)
        {
            for (int i = 0; i < pairs.Count; i++)
            {
                if (pairs[i].comp(a, b))
                {
                    return i;
                }
            }
            return 0;
        }
        //This function finds the position
        //of the pair with the most worktime.
        public int find()
        {
            int pos = 0;
            int num=pairs[0].timeSpent;
            for (int i = 1; i < pairs.Count; i++)
            {
                if (pairs[i].timeSpent > num)
                {
                    num = pairs[i].timeSpent;
                    pos = i;
                }
            }
            return pos;
        }
        
    }
    class Employe{
        public int EmpId;
        public int PrjId;
        public DateTime Datefrom;
        public DateTime DateTo;

        public Employe(String EmployeData) 
        {
            int[] intervals={0,0,0};
            int index=0;
            for (int i = 0; i < EmployeData.Length; i++) 
            {
                if (EmployeData[i] == ' ')
                {
                    intervals[index]+=i;
                    index++;
                }
            }
            index=EmployeData.Length - 2;
            EmpId = ExtractId(EmployeData, 0, intervals[0] - 1);
            PrjId = ExtractId(EmployeData, intervals[0] + 1,
                intervals[1] - intervals[0] - 2);
            Datefrom = ExtractDate(EmployeData, intervals[1] + 1,10);
            if (EmployeData[index] != 'l' && EmployeData[index] != 'L')
            {    
                DateTo = ExtractDate(EmployeData, intervals[2] + 1, 10);
            }
            else
            {
                DateTo = DateTime.Now;
            }
            
        }
        //This function returns an id from the string data.
        public int ExtractId(string EmployeData,int from, int numberOfChars)
        {
            String devide = EmployeData.Substring(from,numberOfChars);
            int id=0;
            Int32.TryParse(devide, out id);
            return id;
        }
        //This function returns a date from string data. 
        public DateTime ExtractDate(string EmployeData, int from, int numberOfChars)
        {
            string devide = EmployeData.Substring(from, numberOfChars);
            string devideDates;
            int[] dates = { 0, 0, 0 };
            devideDates = devide.Substring(0, 4);
            dates[0] = int.Parse(devideDates);
            devideDates = devide.Substring(5, 2);
            dates[1] = int.Parse(devideDates);
            devideDates = devide.Substring(8, 2);
            dates[2] = int.Parse(devideDates);
            DateTime DT = new DateTime(dates[0], dates[1], dates[2]);
            return DT;
        }
        //Returns two employees worktime in days on a single project.
        public int FromDateToDate(Employe employe)
        {
            int Days=0;
            if (DateTo < employe.Datefrom || employe.DateTo < Datefrom)
            {
                return 0;
            }
            else
            {
                if (Datefrom > employe.Datefrom)
                {
                    if (DateTo < employe.DateTo)
                    {
                        Days = (Datefrom - DateTo).Days;
                    }
                    else
                    {
                        Days = (Datefrom - employe.DateTo).Days;
                    }
                }
                else
                {
                    if (DateTo < employe.DateTo)
                    {
                        Days = (employe.Datefrom - DateTo).Days;
                    }
                    else
                    {
                        Days = (employe.Datefrom - employe.DateTo).Days;
                    }
                }
            }
            return -Days;
        }
    }
    class PaiR
    {
        public int id1;
        public int id2;
        public int timeSpent;
        public PaiR(int id, int Id, int ts)
        {
            id1 = id;
            id2 = Id;
            timeSpent = ts;
        }
        public bool comp(int a, int b)
        {
            if(a==id1 && b==id2)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        public void plus(int a)
        {
            timeSpent += a;
        }
    }
}
