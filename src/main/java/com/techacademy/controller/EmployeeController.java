package com.techacademy.controller;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.techacademy.entity.Employee;
import com.techacademy.service.EmployeeService;
@Controller
@RequestMapping("employee")
public class EmployeeController {
    private final EmployeeService service;
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }
    /** トップページを表示 */
    @GetMapping({"/", "/list"})
    public String getHome(Model model) {
        // 全件検索結果をModelに登録
        model.addAttribute("employeeList", service.getAllEmployees());
        // home.htmlに画面遷移
        return "/employee/list";
    }
    /** Employee登録画面を表示 */
    @GetMapping("/create")
    public String getRegister(@ModelAttribute  Employee employee) {
        return "employee/create";
    }
    /** Employee登録処理 */
    @PostMapping("/create")
    public String postRegister(Employee employee) {
        // User登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/employee/list";
    }
    @GetMapping("/details/{id}")
    public String getEmployeeDetails(@PathVariable("id") Integer id, Model model) {
        Optional<Employee> employee = java.util.Optional.empty();
        try {
            //employee = EmployeeService.getEmployeeById(id);
            employee = service.getEmployeeById(id);
        } catch (Exception e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "employee/details";
        } else {
            return "redirect:/employee/list";
        }
    }
    @PostMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id) {
        service.deleteEmployee(id);
        return "redirect:/employee/list";
    }
    // ----- 追加:ここから -----
    /** User更新画面を表示 */
    @GetMapping("/update/{id}/")
    public String getEmployee(@PathVariable("id") Integer id, Model model) {
        // Modelに登録
        model.addAttribute("user", service.getEmployeeById(id));
        // User更新画面に遷移
        return "user/update";
    }

    /** User更新処理 */
    @PostMapping("/update/{id}/")
    public String postUser(Employee employee) {
        // User登録
        service.saveEmployee(employee);
        // 一覧画面にリダイレクト
        return "redirect:/user/list";
    }
}
