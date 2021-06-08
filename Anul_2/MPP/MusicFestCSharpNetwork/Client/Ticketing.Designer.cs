using System.ComponentModel;
using System.Windows.Forms;

namespace MusicFest
{
    partial class Ticketing
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }

            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.dataGridView1 = new System.Windows.Forms.DataGridView();
            this.idShow = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.artistName = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.date = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.venue = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.remainingTickets = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.soldTickets = new System.Windows.Forms.DataGridViewTextBoxColumn();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.nameBox = new System.Windows.Forms.TextBox();
            this.search = new System.Windows.Forms.Button();
            this.buy = new System.Windows.Forms.Button();
            this.label5 = new System.Windows.Forms.Label();
            this.dateTimePicker1 = new System.Windows.Forms.DateTimePicker();
            this.numericUpDown1 = new System.Windows.Forms.NumericUpDown();
            this.logOut = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize) (this.dataGridView1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize) (this.numericUpDown1)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode =
                System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[]
                {this.idShow, this.artistName, this.date, this.venue, this.remainingTickets, this.soldTickets});
            this.dataGridView1.Location = new System.Drawing.Point(12, 11);
            this.dataGridView1.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowTemplate.Height = 24;
            this.dataGridView1.SelectionMode = System.Windows.Forms.DataGridViewSelectionMode.FullRowSelect;
            this.dataGridView1.Size = new System.Drawing.Size(750, 530);
            this.dataGridView1.TabIndex = 1;
            this.dataGridView1.CellClick +=
            new System.Windows.Forms.DataGridViewCellEventHandler(this.dataGridView1_CellClick);
            // 
            // idShow
            // 
            this.idShow.HeaderText = "id";
            this.idShow.Name = "idShow";
            this.idShow.Width = 35;
            // 
            // artistName
            // 
            this.artistName.HeaderText = "artist";
            this.artistName.Name = "artistName";
            // 
            // date
            // 
            this.date.HeaderText = "data";
            this.date.Name = "date";
            // 
            // venue
            // 
            this.venue.HeaderText = "locul";
            this.venue.Name = "venue";
            // 
            // remainingTickets
            // 
            this.remainingTickets.HeaderText = "bilete ramase";
            this.remainingTickets.Name = "remainingTickets";
            // 
            // soldTickets
            // 
            this.soldTickets.HeaderText = "bilete vandute";
            this.soldTickets.Name = "soldTickets";
            // 
            // label1
            // 
            this.label1.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Bold,
                System.Drawing.GraphicsUnit.Point, ((byte) (0)));
            this.label1.ForeColor = System.Drawing.Color.FromArgb(((int) (((byte) (255)))), ((int) (((byte) (164)))),
                ((int) (((byte) (0)))));
            this.label1.Location = new System.Drawing.Point(826, 29);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(202, 38);
            this.label1.TabIndex = 2;
            this.label1.Text = "Caută spectacol";
            // 
            // label2
            // 
            this.label2.Font = new System.Drawing.Font("Segoe UI", 10.2F, System.Drawing.FontStyle.Regular,
                System.Drawing.GraphicsUnit.Point, ((byte) (0)));
            this.label2.ForeColor = System.Drawing.Color.FromArgb(((int) (((byte) (175)))), ((int) (((byte) (208)))),
                ((int) (((byte) (214)))));
            this.label2.Location = new System.Drawing.Point(778, 91);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(100, 38);
            this.label2.TabIndex = 3;
            this.label2.Text = "data:";
            // 
            // label3
            // 
            this.label3.Font = new System.Drawing.Font("Segoe UI", 10.2F, System.Drawing.FontStyle.Regular,
                System.Drawing.GraphicsUnit.Point, ((byte) (0)));
            this.label3.ForeColor = System.Drawing.Color.FromArgb(((int) (((byte) (175)))), ((int) (((byte) (208)))),
                ((int) (((byte) (214)))));
            this.label3.Location = new System.Drawing.Point(778, 276);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(110, 31);
            this.label3.TabIndex = 4;
            this.label3.Text = "cumpărător:";
            // 
            // label4
            // 
            this.label4.Font = new System.Drawing.Font("Segoe UI", 10.2F, System.Drawing.FontStyle.Regular,
                System.Drawing.GraphicsUnit.Point, ((byte) (0)));
            this.label4.ForeColor = System.Drawing.Color.FromArgb(((int) (((byte) (175)))), ((int) (((byte) (208)))),
                ((int) (((byte) (214)))));
            this.label4.Location = new System.Drawing.Point(778, 359);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(100, 36);
            this.label4.TabIndex = 5;
            this.label4.Text = "cantitate:";
            // 
            // nameBox
            // 
            this.nameBox.Location = new System.Drawing.Point(894, 276);
            this.nameBox.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.nameBox.Name = "nameBox";
            this.nameBox.Size = new System.Drawing.Size(174, 27);
            this.nameBox.TabIndex = 6;
            // 
            // search
            // 
            this.search.Location = new System.Drawing.Point(947, 149);
            this.search.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.search.Name = "search";
            this.search.Size = new System.Drawing.Size(96, 38);
            this.search.TabIndex = 7;
            this.search.Text = "caută";
            this.search.UseVisualStyleBackColor = true;
            this.search.Click += new System.EventHandler(this.search_Click);
            // 
            // buy
            // 
            this.buy.Location = new System.Drawing.Point(947, 411);
            this.buy.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.buy.Name = "buy";
            this.buy.Size = new System.Drawing.Size(101, 38);
            this.buy.TabIndex = 8;
            this.buy.Text = "rezervă";
            this.buy.UseVisualStyleBackColor = true;
            this.buy.Click += new System.EventHandler(this.buy_Click);
            // 
            // label5
            // 
            this.label5.Font = new System.Drawing.Font("Segoe UI", 13.8F, System.Drawing.FontStyle.Bold,
                System.Drawing.GraphicsUnit.Point, ((byte) (0)));
            this.label5.ForeColor = System.Drawing.Color.FromArgb(((int) (((byte) (255)))), ((int) (((byte) (164)))),
                ((int) (((byte) (0)))));
            this.label5.Location = new System.Drawing.Point(841, 218);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(163, 31);
            this.label5.TabIndex = 9;
            this.label5.Text = "Rezervă bilet";
            // 
            // dateTimePicker1
            // 
            this.dateTimePicker1.Location = new System.Drawing.Point(894, 88);
            this.dateTimePicker1.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.dateTimePicker1.Name = "dateTimePicker1";
            this.dateTimePicker1.Size = new System.Drawing.Size(174, 27);
            this.dateTimePicker1.TabIndex = 10;
            // 
            // numericUpDown1
            // 
            this.numericUpDown1.Location = new System.Drawing.Point(894, 359);
            this.numericUpDown1.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.numericUpDown1.Name = "numericUpDown1";
            this.numericUpDown1.Size = new System.Drawing.Size(174, 27);
            this.numericUpDown1.TabIndex = 11;
            // 
            // logOut
            // 
            this.logOut.Location = new System.Drawing.Point(947, 479);
            this.logOut.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.logOut.Name = "logOut";
            this.logOut.Size = new System.Drawing.Size(101, 39);
            this.logOut.TabIndex = 12;
            this.logOut.Text = "log out";
            this.logOut.UseVisualStyleBackColor = true;
            this.logOut.Click += new System.EventHandler(this.logOut_Click_1);
            // 
            // Ticketing
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(((int) (((byte) (47)))), ((int) (((byte) (72)))),
                ((int) (((byte) (88)))));
            this.ClientSize = new System.Drawing.Size(1082, 552);
            this.Controls.Add(this.logOut);
            this.Controls.Add(this.numericUpDown1);
            this.Controls.Add(this.dateTimePicker1);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.buy);
            this.Controls.Add(this.search);
            this.Controls.Add(this.nameBox);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.dataGridView1);
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "Ticketing";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Ticketing";
            ((System.ComponentModel.ISupportInitialize) (this.dataGridView1)).EndInit();
            ((System.ComponentModel.ISupportInitialize) (this.numericUpDown1)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        #endregion

        private System.Windows.Forms.Button logOut;
        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.DataGridViewTextBoxColumn soldTickets;
        private System.Windows.Forms.DataGridViewTextBoxColumn remainingTickets;
        private System.Windows.Forms.DataGridViewTextBoxColumn venue;
        private System.Windows.Forms.DataGridViewTextBoxColumn date;
        private System.Windows.Forms.DataGridViewTextBoxColumn artistName;
        private System.Windows.Forms.NumericUpDown numericUpDown1;
        private System.Windows.Forms.DateTimePicker dateTimePicker1;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button buy;
        private System.Windows.Forms.Button search;
        private System.Windows.Forms.TextBox nameBox;
        private System.Windows.Forms.DataGridViewTextBoxColumn idShow;
    }
}