import os

# Definir el nombre de la aplicación y la ruta principal
app_group = "pro"
app_domain = "ddsr"
app_name = f"{app_group}.{app_domain}." + os.path.basename(os.path.abspath(os.path.join(os.path.dirname(__file__), "../")))
main_path = os.path.abspath(os.path.join(os.path.dirname(__file__), "../src/main/java/pro/ddsr/backendantiques"))

# Definir los módulos
modules = [
    "categorias",
    "antiguedades",
    "epoca_antiguedad",
    "estacion_pago",
    "mov_caja",
    "tipom_caja",
    "ranking_antiguedad",
    "clase_contacto",
    "galeria",
    "detalle_transaccion",
    "tipo_direccion",
    "genero",
    "contacto_persona",
    "transacciones",
    "transaccion_medio_pago",
    "medio_pago",
    "tipo_transaccion",
    "coleccionistas",
    "empleado",
    "direccion_persona",
    "sucursal",
    "empresa",
    "estado_persona",
    "ciudades",
    "regiones",
    "paises",
    "historial_propiedad",
    "despachos",
    "tipo_persona",
    "persona_tipo_persona",
    "persona"
]

# Función para capitalizar la primera letra de una cadena y transformar texto con guiones bajos
def capitalize(input_string):
    result = ""
    capitalize_next = True
    for char in input_string:
        if char == '_':
            capitalize_next = True
        elif capitalize_next:
            result += char.upper()
            capitalize_next = False
        else:
            result += char.lower()

    if len(result) > 0:
        result = result[0].upper() + result[1:]

    return result

# Función para generar el contenido de los archivos
def get_file_content(file_type, module_name, app_name):
    capitalized_module = capitalize(module_name)

    if file_type == "Dto":
        return f"""
package {app_name}.modules.{module_name}.dto;

public class {capitalized_module}Dto {{
    // Define attributes here

    // Define constructor(s) here

    // Define getter and setter methods here
}}
"""
    elif file_type == "Repository":
        return f"""
package {app_name}.modules.{module_name}.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import {app_name}.modules.{module_name}.entity.{capitalized_module};
import org.springframework.stereotype.Repository;

@Repository
public interface {capitalized_module}Repository extends JpaRepository<{capitalized_module}, Long> {{
    // Define repository methods here
}}
"""
    elif file_type == "Service":
        return f"""
package {app_name}.modules.{module_name}.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import {app_name}.modules.{module_name}.repository.{capitalized_module}Repository;
import {app_name}.modules.{module_name}.entity.{capitalized_module};

@Service
public class {capitalized_module}Service {{
    @Autowired
    {capitalized_module}Repository {module_name}Repository;

    @Transactional
    public Optional<{capitalized_module}> delete(Long id) {{
        Optional<{capitalized_module}> optional{capitalized_module} = this.{module_name}Repository.findById(id);
        optional{capitalized_module}.ifPresent(
            {capitalized_module}Found -> {{
                this.{module_name}Repository.delete({capitalized_module}Found);
            }}
        );
        return optional{capitalized_module};
    }}
 
    public List<{capitalized_module}> findAll() {{
        return (List<{capitalized_module}>) this.{module_name}Repository.findAll();
    }}

    public Optional<{capitalized_module}> findById(Long id) {{
        return this.{module_name}Repository.findById(id);
    }}

    public {capitalized_module} save({capitalized_module} {capitalized_module}) {{
        return this.{module_name}Repository.save({capitalized_module});
    }}

    public Optional<{capitalized_module}> update(Long id, {capitalized_module} {module_name}) {{
        Optional<{capitalized_module}> optional{capitalized_module} = this.{module_name}Repository.findById(id);
        if (optional{capitalized_module}.isPresent()) {{
            {capitalized_module} {module_name}Item = optional{capitalized_module}.orElseThrow();
            // Sets
            return Optional.of(this.{module_name}Repository.save({module_name}Item));
        }}
        return optional{capitalized_module};
    }}
}}
"""
    elif file_type == "Crud":
        return f"""
package {app_name}.modules.{module_name}.crud;

public interface {capitalized_module}Crud {{
    // Define CRUD methods here
}}
"""
    elif file_type == "Entity":
        return f"""
package {app_name}.modules.{module_name}.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="{module_name}")
public class {capitalized_module} {{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

}}
"""
    elif file_type == "Controller":
      
        return f"""
package {app_name}.modules.{module_name}.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import {app_name}.modules.{module_name}.service.{capitalized_module}Service;
import {app_name}.modules.{module_name}.entity.{capitalized_module};

import jakarta.validation.Valid;

@RestController
@RequestMapping("/{module_name}")
public class {capitalized_module}Controller {{

    @Autowired
    private {capitalized_module}Service {module_name}Service;

    @GetMapping
    @PreAuthorize("hasAuthority('READ')")
    public List<{capitalized_module}> list{capitalized_module}() {{
        return this.{module_name}Service.findAll();
    }}

    @GetMapping("/{{id}}")
    @PreAuthorize("hasAuthority('READ')")
    public ResponseEntity<{capitalized_module}> view(@PathVariable Long id) {{
        Optional<{capitalized_module}> optional{capitalized_module} = {module_name}Service.findById(id);
        if (optional{capitalized_module}.isPresent()) {{
            return ResponseEntity.ok(optional{capitalized_module}.orElseThrow());
        }}
        return ResponseEntity.notFound().build();
    }}

    @PostMapping
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<?> create(@Valid @RequestBody {capitalized_module} {module_name}, BindingResult result) {{
        if (result.hasFieldErrors()) {{
            return validation(result);
        }}
        return ResponseEntity.status(HttpStatus.CREATED).body({module_name}Service.save({module_name}));
    }}

    @PutMapping("/{{id}}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<{capitalized_module}> update(@PathVariable Long id, @Valid @RequestBody {capitalized_module} {module_name}) {{
        Optional<{capitalized_module}> {module_name}Optional = this.{module_name}Service.update(id, {module_name});
        if ({module_name}Optional.isPresent()) {{
            return ResponseEntity.status(HttpStatus.CREATED).body({module_name}Optional.orElseThrow());
        }}
        return ResponseEntity.notFound().build();
    }}

    @DeleteMapping("/{{id}}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<{capitalized_module}> delete(@PathVariable Long id) {{
        Optional<{capitalized_module}> optional{capitalized_module} = this.{module_name}Service.delete(id);
        if (optional{capitalized_module}.isPresent()) {{
            return ResponseEntity.ok(optional{capitalized_module}.orElseThrow());
        }}
        return ResponseEntity.notFound().build();
    }}

    private ResponseEntity<?> validation(BindingResult result) {{
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {{
            errors.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
        }});
        return ResponseEntity.badRequest().body(errors);
    }}

}}
"""

# Función para crear la estructura de carpetas y archivos en vertical slicing
def create_module_structure(modules, app_name):
    # Definir la ruta base
    base_path = os.path.abspath(os.path.join(os.path.dirname(__file__), main_path))

    # Crear las carpetas por cada módulo y dentro de ellas las subcarpetas necesarias
    for module in modules:
        module_path = os.path.join(base_path, "modules", module)
        
        # Subcarpetas para cada módulo
        paths = [
            "dto",
            "repository",
            "service",
            "crud",
            "entity",
            "controller"
        ]
        
        # Crear las subcarpetas dentro de cada módulo
        for path in paths:
            full_path = os.path.join(module_path, path)
            os.makedirs(full_path, exist_ok=True)

        # Crear los archivos para cada módulo
        capitalized_module = capitalize(module)
        files = [
            {"Path": os.path.join(module_path, f"dto/{capitalized_module}Dto.java"), "Type": "Dto"},
            {"Path": os.path.join(module_path, f"repository/{capitalized_module}Repository.java"), "Type": "Repository"},
            {"Path": os.path.join(module_path, f"service/{capitalized_module}Service.java"), "Type": "Service"},
            {"Path": os.path.join(module_path, f"crud/{capitalized_module}Crud.java"), "Type": "Crud"},
            {"Path": os.path.join(module_path, f"entity/{capitalized_module}.java"), "Type": "Entity"},
            {"Path": os.path.join(module_path, f"controller/{capitalized_module}Controller.java"), "Type": "Controller"}
        ]
        
        # Crear los archivos si no existen
        for file in files:
            if not os.path.exists(file["Path"]):
                with open(file["Path"], "w") as f:
                    content = get_file_content(file["Type"], module, app_name)
                    f.write(content)

# Crear la estructura vertical slicing
create_module_structure(modules, app_name)
