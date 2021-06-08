using System.Windows.Forms;

namespace FestivalGUI
{
    partial class MainForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

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
            this.mainDataView = new System.Windows.Forms.DataGridView();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.dataSearchText = new System.Windows.Forms.TextBox();
            this.search = new System.Windows.Forms.Button();
            this.searchDataView = new System.Windows.Forms.DataGridView();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label5 = new System.Windows.Forms.Label();
            this.numeText = new System.Windows.Forms.TextBox();
            this.locText = new System.Windows.Forms.TextBox();
            this.addButton = new System.Windows.Forms.Button();
            this.logoutButton = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize) (this.mainDataView)).BeginInit();
            ((System.ComponentModel.ISupportInitialize) (this.searchDataView)).BeginInit();
            this.SuspendLayout();
            // 
            // mainDataView
            // 
            this.mainDataView.ColumnHeadersHeightSizeMode =
                System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.mainDataView.Location = new System.Drawing.Point(14, 16);
            this.mainDataView.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.mainDataView.Name = "mainDataView";
            this.mainDataView.RowHeadersWidth = 62;
            this.mainDataView.Size = new System.Drawing.Size(1252, 375);
            this.mainDataView.TabIndex = 0;
            this.mainDataView.CellContentClick +=
                new System.Windows.Forms.DataGridViewCellEventHandler(this.mainDataView_CellContentClick);
            this.mainDataView.CellFormatting +=
                new System.Windows.Forms.DataGridViewCellFormattingEventHandler(this.mainDataView_CellFormatting);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(41, 425);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(155, 25);
            this.label1.TabIndex = 1;
            this.label1.Text = "Cautare spectacol:";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(46, 482);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(53, 25);
            this.label2.TabIndex = 2;
            this.label2.Text = "Data:";
            // 
            // dataSearchText
            // 
            this.dataSearchText.Location = new System.Drawing.Point(132, 482);
            this.dataSearchText.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.dataSearchText.Name = "dataSearchText";
            this.dataSearchText.Size = new System.Drawing.Size(111, 31);
            this.dataSearchText.TabIndex = 3;
            // 
            // search
            // 
            this.search.Location = new System.Drawing.Point(88, 569);
            this.search.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.search.Name = "search";
            this.search.Size = new System.Drawing.Size(98, 39);
            this.search.TabIndex = 4;
            this.search.Text = "Cautare";
            this.search.UseVisualStyleBackColor = true;
            this.search.Click += new System.EventHandler(this.search_Click);
            // 
            // searchDataView
            // 
            this.searchDataView.AllowUserToAddRows = false;
            this.searchDataView.AllowUserToDeleteRows = false;
            this.searchDataView.ColumnHeadersHeightSizeMode =
                System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.searchDataView.Location = new System.Drawing.Point(329, 449);
            this.searchDataView.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.searchDataView.Name = "searchDataView";
            this.searchDataView.ReadOnly = true;
            this.searchDataView.RowHeadersWidth = 62;
            this.searchDataView.RowTemplate.Height = 28;
            this.searchDataView.Size = new System.Drawing.Size(1141, 240);
            this.searchDataView.TabIndex = 5;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(1273, 64);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(132, 25);
            this.label3.TabIndex = 6;
            this.label3.Text = "Adaugare bilet:";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(1273, 112);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(64, 25);
            this.label4.TabIndex = 7;
            this.label4.Text = "Nume:";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(1273, 156);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(83, 25);
            this.label5.TabIndex = 8;
            this.label5.Text = "Nr locuri:";
            // 
            // numeText
            // 
            this.numeText.Location = new System.Drawing.Point(1341, 109);
            this.numeText.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.numeText.Name = "numeText";
            this.numeText.Size = new System.Drawing.Size(178, 31);
            this.numeText.TabIndex = 9;
            // 
            // locText
            // 
            this.locText.Location = new System.Drawing.Point(1358, 156);
            this.locText.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.locText.Name = "locText";
            this.locText.Size = new System.Drawing.Size(83, 31);
            this.locText.TabIndex = 10;
            // 
            // addButton
            // 
            this.addButton.Location = new System.Drawing.Point(1317, 234);
            this.addButton.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.addButton.Name = "addButton";
            this.addButton.Size = new System.Drawing.Size(136, 44);
            this.addButton.TabIndex = 11;
            this.addButton.Text = "Adauga bilet";
            this.addButton.UseVisualStyleBackColor = true;
            this.addButton.Click += new System.EventHandler(this.addButton_Click);
            // 
            // logoutButton
            // 
            this.logoutButton.Location = new System.Drawing.Point(14, 689);
            this.logoutButton.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.logoutButton.Name = "logoutButton";
            this.logoutButton.Size = new System.Drawing.Size(99, 48);
            this.logoutButton.TabIndex = 12;
            this.logoutButton.Text = "Logout";
            this.logoutButton.UseVisualStyleBackColor = true;
            this.logoutButton.Click += new System.EventHandler(this.logoutButton_Click);
            // 
            // MainForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(10F, 25F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1530, 755);
            this.Controls.Add(this.logoutButton);
            this.Controls.Add(this.addButton);
            this.Controls.Add(this.locText);
            this.Controls.Add(this.numeText);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.searchDataView);
            this.Controls.Add(this.search);
            this.Controls.Add(this.dataSearchText);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.mainDataView);
            this.Margin = new System.Windows.Forms.Padding(3, 4, 3, 4);
            this.Name = "MainForm";
            this.Text = "MainForm";
            this.Load += new System.EventHandler(this.MainForm_Load);
            ((System.ComponentModel.ISupportInitialize) (this.mainDataView)).EndInit();
            ((System.ComponentModel.ISupportInitialize) (this.searchDataView)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        #endregion

        private System.Windows.Forms.DataGridView mainDataView;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox dataSearchText;
        private System.Windows.Forms.Button search;
        private System.Windows.Forms.DataGridView searchDataView;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox numeText;
        private System.Windows.Forms.TextBox locText;
        private System.Windows.Forms.Button addButton;
        private System.Windows.Forms.Button logoutButton;
    }
}