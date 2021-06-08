using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace practic_marire_c
{
    class Issue
    {
        string summary, description, assignTo, registeredBy;
        IssueType type;
        StatusType status;
        DateTime date;

        public Issue(string summary, string description, string assignTo, string registeredBy, IssueType type, StatusType status, DateTime date)
        {
            this.summary = summary;
            this.description = description;
            this.assignTo = assignTo;
            this.registeredBy = registeredBy;
            this.type = type;
            this.status = status;
            this.date = date;
        }

        public string Summary { get => summary; set => summary = value; }
        public string Description { get => description; set => description = value; }
        public string AssignTo { get => assignTo; set => assignTo = value; }
        public DateTime Date { get => date; set => date = value; }
        public string RegisteredBy { get => registeredBy; set => registeredBy = value; }
        internal IssueType Type { get => type; set => type = value; }
        internal StatusType Status { get => status; set => status = value; }

        public override string ToString()
        {
            return Summary + " -- " + AssignTo + " -- " + Type + " -- " + Status + " -- " + Date;
        }
    }
}
