# Execução passo a passo

---

Para executar o projeto, acesse o link do repositório abaixo. Lá, você poderá clonar o projeto em seu computador e executá-lo. 

https://github.com/Augustoo22/api_bank/tree/main

Agora que você acessou o repositório, siga o passo a passo abaixo:

### Passo 1

---

![image.png](image.png)

Ao acessar o repositório, sua tela estará semelhante à imagem abaixo. Para continuar, clique no botão "Code".

### Passo 2

---

![image.png](image%201.png)

Após clicar no botão "Code", uma janela será aberta. Nela, copie o endereço que está destacado, conforme mostrado na imagem.

### Passo 3

---

![image.png](image%202.png)

Após copiar o endereço, abra o **cmd** ou **Git Bash** no diretório desejado. Digite ou cole o comando abaixo, e após isso, pressione **Enter** para executar:

```powershell
git clone https://github.com/Augustoo22/api_bank.git
```

### Passo 4

---

![image.png](image%203.png)

Após executar o comando, abra a IDE de sua preferência (como IntelliJ, VSCode, Eclipse, etc.).

### Passo 5

---

![image.png](image%204.png)

Na IDE que você escolheu, clique em **"Open Folder"** para abrir o diretório onde o projeto foi clonado.

### Passo 6

---

![image.png](image%205.png)

Abra a pasta **"api_bank"**, que contém o projeto

### Passo 7

---

![image.png](image%206.png)

Dentro do projeto, no terminal da sua IDE, execute o comando:

```powershell
cd .\apibank\
```

Isso irá te direcionar para o diretório principal do projeto "api_bank".

### Passo 8

---

![image.png](image%207.png)

Agora, será necessário abrir o Docker. Se você ainda não o tem instalado, veja os links abaixo para instruções de instalação:

***Como abaixar docker:***

[https://www.youtube.com/watch?v=7WuZU7xP2ng](https://www.youtube.com/watch?v=7WuZU7xP2ng)

[https://docs.docker.com/desktop/install/windows-install/](https://docs.docker.com/desktop/install/windows-install/)

### Passo 9

---

![image.png](image%208.png)

Para executar o projeto em Docker, você precisará usar o seguinte comando:

```powershell
docker-compose up --build
```

Esse comando realiza duas ações ao mesmo tempo:

1. **Reconstrói** as imagens Docker, se necessário.
2. **Inicia** os contêineres definidos no arquivo `docker-compose.yml`.

### Passo 10

---

![image.png](image%209.png)

Acesse o container “apibank”

### Passo 11

---

![image.png](image%2010.png)

Acesse a URL 8080

### Passo 12

---

![image.png](image%2011.png)

Acesse a URL `localhost:8080/usuarios`

### Passo 13

---

![image.png](image%2012.png)

Você verá a aplicação em execução. Para começar a interagir com ela, crie uma conta conforme necessário.

### Passo 14

---

![image.png](image%2013.png)

Após preencher todos os campos, clique em **"Enviar"** para concluir o processo de criação da conta.

### Passo 15

---

![image.png](image%2014.png)

Com a conta criada, acesse a plataforma usando suas credenciais recém-criadas.

### Passo 16

---

![image.png](image%2015.png)

Dentro da plataforma, para realizar uma operação como um PIX, acesse a página específica usando o link destacado.

### Passo 17

---

![image.png](image%2016.png)

Na área de PIX, insira a chave (que é um ID) no campo apropriado e coloque o valor no outro campo. Após preencher as informações, clique em **"Enviar"**. Em seguida, você verá o saldo diminuir conforme a transação realizada.

### Passo 18

---

![image.png](image%2017.png)

Agora, para editar seu cadastro, acesse o link destacado.

### Passo 19

---

![image.png](image%2018.png)

No exemplo, altere o nome de **"Exemplo1"** para **"Exemplo10"**.

### Passo 20

---

![image.png](image%2019.png)

Após a edição, você poderá visualizar o registro do histórico de PIX realizados.

### Passo 21

---

![image.png](image%2020.png)

Aqui estão os registros dos PIX realizados. Dessa forma, você pode verificar como a aplicação é executada e como as dependências são baixadas.