using System.ComponentModel;

namespace MusicFest
{
    partial class SearchResult
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
            this.back = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize) (this.dataGridView1)).BeginInit();
            this.SuspendLayout();
            // 
            // dataGridView1
            // 
            this.dataGridView1.ColumnHeadersHeightSizeMode =
                System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridView1.Columns.AddRange(new System.Windows.Forms.DataGridViewColumn[]
                {this.idShow, this.artistName, this.date, this.venue, this.remainingTickets, this.soldTickets});
            this.dataGridView1.Location = new System.Drawing.Point(12, 12);
            this.dataGridView1.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.dataGridView1.Name = "dataGridView1";
            this.dataGridView1.RowTemplate.Height = 24;
            this.dataGridView1.Size = new System.Drawing.Size(764, 396);
            this.dataGridView1.TabIndex = 0;
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
            // back
            // 
            this.back.Location = new System.Drawing.Point(648, 412);
            this.back.Margin = new System.Windows.Forms.Padding(3, 2, 3, 2);
            this.back.Name = "back";
            this.back.Size = new System.Drawing.Size(128, 38);
            this.back.TabIndex = 1;
            this.back.Text = "close";
            this.back.UseVisualStyleBackColor = true;
            this.back.Click += new System.EventHandler(this.back_Click);
            // 
            // SearchResult
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 20F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(((int) (((byte) (47)))), ((int) (((byte) (72)))),
                ((int) (((byte) (88)))));
            this.ClientSize = new System.Drawing.Size(788, 462);
            this.Controls.Add(this.back);
            this.Controls.Add(this.dataGridView1);
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "SearchResult";
            this.Text = "SearchResult";
            ((System.ComponentModel.ISupportInitialize) (this.dataGridView1)).EndInit();
            this.ResumeLayout(false);
        }

        #endregion

        private System.Windows.Forms.DataGridView dataGridView1;
        private System.Windows.Forms.Button back;
        private System.Windows.Forms.DataGridViewTextBoxColumn soldTickets;
        private System.Windows.Forms.DataGridViewTextBoxColumn remainingTickets;
        private System.Windows.Forms.DataGridViewTextBoxColumn venue;
        private System.Windows.Forms.DataGridViewTextBoxColumn date;
        private System.Windows.Forms.DataGridViewTextBoxColumn artistName;
        private System.Windows.Forms.DataGridViewTextBoxColumn idShow;
    }
}