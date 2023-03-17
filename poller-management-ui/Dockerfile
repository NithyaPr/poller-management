# ==== CONFIGURE =====
# Use a Node 16 base image
FROM node:16-alpine as builder
# Set the working directory to /app inside the container
WORKDIR /app
# Copy app files
COPY package.json .
COPY yarn.lock .
# ==== BUILD =====
# Install dependencies (npm ci makes sure the exact versions in the lockfile gets installed)
RUN yarn install
COPY . . 

# Build the app
RUN yarn build
# ==== RUN =======

FROM nginx:1.21.0-alpine
WORKDIR /usr/share/nginx/html
RUN rm -rf ./*
# Copy built assets from `builder` image
COPY --from=builder /app/build .
# Add your nginx.conf
##COPY nginx.conf /etc/nginx/conf.d/default.conf
# Expose port
##EXPOSE 80
# Start nginx
ENTRYPOINT ["nginx", "-g", "daemon off;"]
