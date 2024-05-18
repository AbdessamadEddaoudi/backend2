package ma.zs.carriere;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.openfeign.EnableFeignClients;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ma.zs.carriere.bean.core.commun.Employe;
import ma.zs.carriere.service.facade.admin.commun.EmployeAdminService;
import ma.zs.carriere.zynerator.security.bean.*;
import ma.zs.carriere.zynerator.security.common.AuthoritiesConstants;
import ma.zs.carriere.zynerator.security.service.facade.*;


import ma.zs.carriere.zynerator.security.bean.User;
import ma.zs.carriere.zynerator.security.bean.Role;

@SpringBootApplication
//@EnableFeignClients
public class CarriereApplication {
    public static ConfigurableApplicationContext ctx;

    public static void main(String[] args) {
        ctx=SpringApplication.run(CarriereApplication.class, args);
    }


    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    public static ConfigurableApplicationContext getCtx() {
        return ctx;
    }

    @Bean
    public CommandLineRunner demo(UserService userService, RoleService roleService, ModelPermissionService modelPermissionService, ActionPermissionService actionPermissionService, ModelPermissionUserService modelPermissionUserService , EmployeAdminService employeService) {
    return (args) -> {
        if(true){


        // ModelPermissions
        List<ModelPermission> modelPermissions = new ArrayList<>();
        addPermission(modelPermissions);
        modelPermissions.forEach(e -> modelPermissionService.create(e));
        // ActionPermissions
        List<ActionPermission> actionPermissions = new ArrayList<>();
        addActionPermission(actionPermissions);
        actionPermissions.forEach(e -> actionPermissionService.create(e));

		// User Admin
        User userForAdmin = new User("admin");
		userForAdmin.setPassword("123");
		// Role Admin
		Role roleForAdmin = new Role();
		roleForAdmin.setAuthority(AuthoritiesConstants.ADMIN);
		roleForAdmin.setCreatedAt(LocalDateTime.now());
		Role roleForAdminSaved = roleService.create(roleForAdmin);
		RoleUser roleUserForAdmin = new RoleUser();
		roleUserForAdmin.setRole(roleForAdminSaved);
		if (userForAdmin.getRoleUsers() == null)
			userForAdmin.setRoleUsers(new ArrayList<>());

		userForAdmin.getRoleUsers().add(roleUserForAdmin);
		if (userForAdmin.getModelPermissionUsers() == null)
			userForAdmin.setModelPermissionUsers(new ArrayList<>());


        userForAdmin.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        userService.create(userForAdmin);

		// User Employe
        Employe userForEmploye = new Employe("employe");
		userForEmploye.setPassword("123");
		// Role Employe
		Role roleForEmploye = new Role();
		roleForEmploye.setAuthority(AuthoritiesConstants.EMPLOYE);
		roleForEmploye.setCreatedAt(LocalDateTime.now());
		Role roleForEmployeSaved = roleService.create(roleForEmploye);
		RoleUser roleUserForEmploye = new RoleUser();
		roleUserForEmploye.setRole(roleForEmployeSaved);
		if (userForEmploye.getRoleUsers() == null)
			userForEmploye.setRoleUsers(new ArrayList<>());

		userForEmploye.getRoleUsers().add(roleUserForEmploye);
		if (userForEmploye.getModelPermissionUsers() == null)
			userForEmploye.setModelPermissionUsers(new ArrayList<>());


        userForEmploye.setModelPermissionUsers(modelPermissionUserService.initModelPermissionUser());

        employeService.create(userForEmploye);

            }
        };
    }




    private static String fakeString(String attributeName, int i) {
        return attributeName + i;
    }

    private static Long fakeLong(String attributeName, int i) {
        return  10L * i;
    }
    private static Integer fakeInteger(String attributeName, int i) {
        return  10 * i;
    }

    private static Double fakeDouble(String attributeName, int i) {
        return 10D * i;
    }

    private static BigDecimal fakeBigDecimal(String attributeName, int i) {
        return  BigDecimal.valueOf(i*1L*10);
    }

    private static Boolean fakeBoolean(String attributeName, int i) {
        return i % 2 == 0 ? true : false;
    }
    private static LocalDateTime fakeLocalDateTime(String attributeName, int i) {
        return LocalDateTime.now().plusDays(i);
    }


    private static void addPermission(List<ModelPermission> modelPermissions) {
        modelPermissions.add(new ModelPermission("Mandat"));
        modelPermissions.add(new ModelPermission("Diplome"));
        modelPermissions.add(new ModelPermission("EtatDemandeDocument"));
        modelPermissions.add(new ModelPermission("TemplateDocument"));
        modelPermissions.add(new ModelPermission("DemandeDocument"));
        modelPermissions.add(new ModelPermission("TypeDocument"));
        modelPermissions.add(new ModelPermission("ResponsabiliteDetail"));
        modelPermissions.add(new ModelPermission("Responsabilite"));
        modelPermissions.add(new ModelPermission("Employe"));
        modelPermissions.add(new ModelPermission("Echelon"));
        modelPermissions.add(new ModelPermission("Avancement"));
        modelPermissions.add(new ModelPermission("EntiteAdmin"));
        modelPermissions.add(new ModelPermission("Echelle"));
        modelPermissions.add(new ModelPermission("User"));
        modelPermissions.add(new ModelPermission("ModelPermission"));
        modelPermissions.add(new ModelPermission("ActionPermission"));
    }

    private static void addActionPermission(List<ActionPermission> actionPermissions) {
        actionPermissions.add(new ActionPermission("list"));
        actionPermissions.add(new ActionPermission("create"));
        actionPermissions.add(new ActionPermission("delete"));
        actionPermissions.add(new ActionPermission("edit"));
        actionPermissions.add(new ActionPermission("view"));
        actionPermissions.add(new ActionPermission("duplicate"));
    }


}


