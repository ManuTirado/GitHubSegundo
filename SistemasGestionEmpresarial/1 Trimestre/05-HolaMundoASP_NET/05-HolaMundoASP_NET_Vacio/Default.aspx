<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Default.aspx.cs" Inherits="_Default" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
            <asp:Label ID="Label2" runat="server" Text="Nombre: "></asp:Label>
            <asp:TextBox ID="txtNombre" runat="server" Width="242px"></asp:TextBox>
            <asp:Label ID="lblError" runat="server" ForeColor="Red" Text="El campo nombre no puede estar vacío" Visible="False"></asp:Label>
        </div>
        <p>
            <asp:Button ID="btnSaludar" runat="server" Text="Saludar" OnClick="btnSaludar_Click" />
        </p>
        <p>
            <asp:Label ID="lblSaludo" runat="server" Visible="False"></asp:Label>
        </p>
    </form>
    </body>
</html>
