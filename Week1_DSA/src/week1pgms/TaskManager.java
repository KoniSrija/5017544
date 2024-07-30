package week1pgms;

import java.util.*;

class Task {
    private int taskId;
    private String taskName;
    private String status;

  
    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

class TaskNode {
    Task task;
    TaskNode next;

    public TaskNode(Task task) {
        this.task = task;
        this.next = null;
    }
}

class TaskManager {
    private TaskNode head=null;
    public void addTask(Task task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public Task searchTask(int taskId) {
        TaskNode temp = head;
        while (temp != null) {
            if (temp.task.getTaskId() == taskId) {
                return temp.task;
            }
            temp = temp.next;
        }
        return null; 
    }
   
    public void traverseTasks() {
        TaskNode temp = head;
        while (temp != null) {
            System.out.println(temp.task);
            temp = temp.next;
        }
    }

    public boolean deleteTask(int taskId) {
        if (head == null) {
            return false; 
        }

        if (head.task.getTaskId() == taskId) {
            head = head.next;
            return true; 
        }

        TaskNode prev = null;
        TaskNode curr = head;
        while (curr != null && curr.task.getTaskId() != taskId) {
            prev = curr;
            curr = curr.next;
        }

        if (curr == null) {
            return false; 
        }

        prev.next = curr.next;
        return true; 
    }

    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        Scanner sc = new Scanner(System.in);
        int k=0;
        do {
        	System.out.println("1.Add 2.Search 3.Traverse 4.Delete 5.Exit");
    		System.out.println("Choose an option:");
    		 k = sc.nextInt();
    		switch(k)
    		{
    		case 1:  System.out.println("Enter id,name,status:");
    			   manager.addTask(new Task(sc.nextInt(), sc.nextLine(), sc.nextLine()));
    		        break;
    		case 2:   System.out.println("Enter the id you want to search");
    		          int searchid=sc.nextInt();
                      Task task = manager.searchTask(searchid);
                       if (task != null) {
                      System.out.println(task);
                      } else {
                     System.out.println("Task not found.");
                      }
                      break;
    		case 3:    System.out.println("All tasks:");
                        manager.traverseTasks();
                        break;
    		case 4:   System.out.println("Enter id you want to delete");
    	            	 boolean deleted = manager.deleteTask(3);
    	                if (deleted) {
    	                System.out.println("Task deleted successfully.");
    	              } else {
    	               System.out.println("Task not found.");
    	               }
    		case 5: System.out.println("Exiting..");
			          break;
	       	default: System.out.println("Invalid choice");
		}
       }while(k!=5);
      
    }
}


