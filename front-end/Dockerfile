# Sử dụng node:14-alpine như image cơ sở
FROM node:14-alpine

# Tạo thư mục làm việc
WORKDIR /front-end

# Sao chép package.json và package-lock.json vào container
COPY package*.json ./

# Cài đặt các dependencies
RUN npm install

# Sao chép mã nguồn của ứng dụng vào container
COPY . .

# Tạo bản build production
RUN npm run build

# Expose port 3000 để có thể truy cập ứng dụng
EXPOSE 3000

# Chạy ứng dụng khi container khởi động
CMD ["npm", "start"]