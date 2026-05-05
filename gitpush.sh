#!/bin/bash

# =============================================
# Script Git Push Automático para Spring Boot
# Uso: ./gitpush.sh
# =============================================

echo "🚀 Iniciando proceso Git..."

# Colores para mejor visualización
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# 1. Verificar si ya es un repositorio Git
if [ ! -d ".git" ]; then
    echo -e "${YELLOW}⚠️  No se encontró repositorio Git. Inicializando...${NC}"
    git init
    git branch -M main
    echo -e "${GREEN}✅ Repositorio Git inicializado con rama main${NC}"
fi

# 2. Agregar todos los archivos
echo -e "${YELLOW}📂 Agregando archivos...${NC}"
git add .

# 3. Verificar si hay cambios para commitear
if git diff --cached --quiet; then
    echo -e "${RED}❌ No hay cambios para commitear.${NC}"
    exit 1
fi

# 4. Pedir mensaje de commit
echo -e "${YELLOW}✍️  Escribe tu mensaje de commit:${NC}"
read -r commit_message

if [ -z "$commit_message" ]; then
    commit_message="Actualización automática $(date '+%Y-%m-%d %H:%M')"
    echo -e "${YELLOW}Usando mensaje por defecto: $commit_message${NC}"
fi

# 5. Hacer commit
git commit -m "$commit_message"
echo -e "${GREEN}✅ Commit realizado${NC}"

# 6. Push
echo -e "${YELLOW}📤 Subiendo cambios a GitHub...${NC}"
if git push -u origin main; then
    echo -e "${GREEN}🎉 ¡Push completado exitosamente!${NC}"
else
    echo -e "${RED}❌ Error al hacer push.${NC}"
    echo -e "${YELLOW}Sugerencia: Prueba con 'git pull origin main --rebase' si hay conflicto.${NC}"
fi