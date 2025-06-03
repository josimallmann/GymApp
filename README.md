# 🏋️‍♀️ GymApp

**GymApp** é uma aplicação backend desenvolvida com Spring Boot que permite o gerenciamento de treinos físicos. Os treinos incluem informações como tipo de exercício, distância, tempo, data e ritmo médio.

---

## 📚 Tecnologias Utilizadas

- ✅ **Java 17**
- ✅ **Spring Boot 3.5**
- ✅ **Spring Data JPA**
- ✅ **H2 Database (banco de dados em memória)**
- ✅ **Lombok**
- ✅ **Jakarta Persistence API**

---

## ⚙️ Como Executar

1. **Clone o repositório**:

   ```bash
   git clone https://github.com/seu-usuario/GymApp.git
   cd GymApp
2. **Execute o projeto via Maven**:

./mvnw spring-boot:run
ou via sua IDE preferida (IntelliJ, Eclipse, VS Code).

3. Acesse o console do banco H2 (útil para visualizar os dados):

http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Username: sa
Password: (deixe em branco)

🧩 Endpoints Disponíveis (exemplo)
Método	Endpoint	Descrição
GET	/treinos	Lista todos os treinos
GET	/treinos/{id}	Busca treino por ID
POST	/treinos	Cria um novo treino
PUT	/treinos/{id}	Atualiza treino existente
DELETE	/treinos/{id}	Remove treino pelo ID

✨ Funcionalidades
Registro e consulta de treinos físicos

Atualização de dados como tempo, ritmo, distância, tipo de exercício

Validações básicas e persistência via JPA

Banco em memória (ideal para testes)

