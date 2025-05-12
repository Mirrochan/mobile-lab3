using System;
using System.Collections.ObjectModel;
using System.ComponentModel;
using System.Linq;
using System.Windows.Input;
using lab3.Models;

public class MainViewModel : INotifyPropertyChanged
{
    private string _newTaskDescription;
    public string NewTaskDescription
    {
        get => _newTaskDescription;
        set { _newTaskDescription = value; OnPropertyChanged(nameof(NewTaskDescription)); }
    }

    private string _searchText;
    public string SearchText
    {
        get => _searchText;
        set { _searchText = value; OnPropertyChanged(nameof(SearchText)); FilterTasks(); }
    }

    private TaskItem _selectedTask;
    public TaskItem SelectedTask
    {
        get => _selectedTask;
        set { _selectedTask = value; OnPropertyChanged(nameof(SelectedTask)); }
    }

    public ObservableCollection<TaskItem> Tasks { get; } = new ObservableCollection<TaskItem>();
    public ObservableCollection<TaskItem> FilteredTasks { get; } = new ObservableCollection<TaskItem>();

    public ICommand AddTaskCommand { get; }
    public ICommand DeleteTaskCommand { get; }
    public ICommand EditTaskCommand { get; }

    public MainViewModel()
    {
        AddTaskCommand = new RelayCommand(AddTask, CanAddTask);
        DeleteTaskCommand = new RelayCommand<TaskItem>(DeleteTask);
        EditTaskCommand = new RelayCommand(EditTask, () => SelectedTask != null && !string.IsNullOrWhiteSpace(NewTaskDescription));
    }

    private void AddTask()
    {
        var task = new TaskItem { Description = NewTaskDescription };
        Tasks.Add(task);
        FilterTasks();
        NewTaskDescription = string.Empty;
    }

    private void DeleteTask(TaskItem task)
    {
        if (task != null)
        {
            Tasks.Remove(task);
            FilterTasks();
        }
    }

    private void EditTask()
    {
        if (SelectedTask != null && !string.IsNullOrWhiteSpace(NewTaskDescription))
        {
            SelectedTask.Description = NewTaskDescription;
            NewTaskDescription = string.Empty;
            FilterTasks();
        }
    }

    private void FilterTasks()
    {
        FilteredTasks.Clear();
        var filtered = string.IsNullOrWhiteSpace(SearchText)
            ? Tasks
            : Tasks.Where(t => t.Description.ToLower().Contains(SearchText.ToLower()));
        foreach (var task in filtered)
            FilteredTasks.Add(task);
    }

    private bool CanAddTask() => !string.IsNullOrWhiteSpace(NewTaskDescription);

    public event PropertyChangedEventHandler PropertyChanged;
    protected void OnPropertyChanged(string name) =>
        PropertyChanged?.Invoke(this, new PropertyChangedEventArgs(name));
}
