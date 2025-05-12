using System;
namespace lab3.Models
{
    public class TaskItem
    {
        public string Description { get; set; }
        private bool _isCompleted;

        public bool IsCompleted
        {
            get => _isCompleted;
            set
            {
                if (_isCompleted != value)
                {
                    _isCompleted = value;
                    OnStatusChanged();
                }
            }
        }

        public delegate void StatusChangedEventHandler(object sender, EventArgs e);
        public event StatusChangedEventHandler StatusChanged;

        protected virtual void OnStatusChanged()
        {
            StatusChanged?.Invoke(this, EventArgs.Empty);
        }
    }
}
