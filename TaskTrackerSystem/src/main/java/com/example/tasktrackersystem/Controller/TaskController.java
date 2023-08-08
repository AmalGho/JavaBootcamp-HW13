package com.example.tasktrackersystem.Controller;

import com.example.tasktrackersystem.ApiResponse.ApiResponse;
import com.example.tasktrackersystem.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {

    ArrayList<Task> tasks = new ArrayList<>();

    @PostMapping("/create")
    public ApiResponse createTask(@RequestBody Task task) {
        tasks.add(task);
        return new ApiResponse("task added");
    }

    @GetMapping("/display")
    public ArrayList<Task> displayAllTask() {
        return tasks;
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody Task task) {
        tasks.set(index, task);
        return new ApiResponse("task updated");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index) {
        tasks.remove(index);
        return new ApiResponse("task deleted");
    }

    @PutMapping("/updateStatus/{index}")
    public ApiResponse updateStatus(@PathVariable int index) {
        if (tasks.get(index).getStatus().equalsIgnoreCase("done")) {
            tasks.get(index).setStatus("not done");
        }else {
            tasks.get(index).setStatus("done");
        }

        return new ApiResponse("status updated");
    }

    @GetMapping("/search/{title}")
    public ApiResponse searchTask(@PathVariable String title) {
        for (Task t : tasks){
            if (t.getTitle().equalsIgnoreCase(title)) {
                return new ApiResponse("the searched title found, " + t);
            }
        }
        return new ApiResponse(title + " title not found");
    }
}
