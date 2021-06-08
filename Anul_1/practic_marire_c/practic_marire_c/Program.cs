using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;

namespace practic_marire_c
{
    class Program { 

        static IList<Issue> issues = new List<Issue>();

        static void ReadFromFile()
        {
            using (StreamReader file = new StreamReader("C:\\Users\\Razvan\\Desktop\\practic_marire_c\\practic_marire_c\\issues.txt"))
            {
                string line;
                while ((line = file.ReadLine()) != null) //read line by line
                {
                    string[] arguments = line.Split(':'); //split by regex
                    string summary = arguments[0];
                    string description = arguments[1];
                    IssueType type;
                    switch(arguments[2])
                    {
                        case "Bug":
                            type = IssueType.Bug;
                            break;
                        default:
                            type = IssueType.Task;
                            break;
                    }
                    string assignedTo = arguments[3];
                    string registeredBy = arguments[4];
                    StatusType status;
                    switch(arguments[5])
                    {
                        case "Open":
                            status = StatusType.Open;
                            break;
                        default:
                            status = StatusType.Closed;
                            break;
                    }
                    DateTime date = Convert.ToDateTime(arguments[6]);
                    Issue issue = new Issue(summary, description, assignedTo, registeredBy, type, status, date);
                    issues.Add(issue);
                }
            }
        }

        static void FilterByType()
        {
            int bugs = issues.Where(i => i.Type == IssueType.Bug).Count();
            int tasks = issues.Where(i => i.Type == IssueType.Task).Count();
            int numberOfIssues = issues.Count();
            Console.WriteLine((float)(bugs * 100) / numberOfIssues + "% bugs");
            Console.WriteLine((float)(tasks * 100) / numberOfIssues + "% tasks");
        }

        static void Main(string[] args)
        {
            ReadFromFile();
            Console.WriteLine("---- All the issues ----");
            issues.ToList().ForEach(Console.WriteLine);
            Console.WriteLine("\n ---- First filter ----");
            FilterByType();
            Console.WriteLine("\n ---- Second filter ----");
            FilterByStatusPerMonthPerDeveloper(2);
            Console.ReadKey();
        }

        private static void FilterByStatusPerMonthPerDeveloper(int month)
        {
            IList<string> devs = issues.Select(i => i.AssignTo).Distinct().ToList();
            foreach(string dev in devs)
            {
                int issuesForDev = issues.Where(i => i.AssignTo == dev && i.Date.Month == month).Count();
                if(issuesForDev > 0)
                {
                    int closedIssuesForDev = issues.Where(i => i.AssignTo == dev && i.Date.Month == month && i.Status == StatusType.Closed).Count();
                    Console.WriteLine("For " + dev + " in month " + month + " : " + (float)(closedIssuesForDev * 100) / issuesForDev + "% closed");
                }
                else
                {
                    Console.WriteLine("No issues for " + dev + " in month " + month);
                }
                
            }
        }
    }
}
