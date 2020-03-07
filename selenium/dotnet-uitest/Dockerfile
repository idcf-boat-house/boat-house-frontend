#See https://aka.ms/containerfastmode to understand how Visual Studio uses this Dockerfile to build your images for faster debugging.

# FROM mcr.microsoft.com/dotnet/core/runtime:3.1-buster-slim AS base
# WORKDIR /app

# copy csproj and restore as distinct layers
FROM mcr.microsoft.com/dotnet/core/sdk:3.1-buster AS build
WORKDIR /app
COPY ./UITest/UITest.csproj .
RUN dotnet restore
COPY ./UITest/src ./src
ENTRYPOINT dotnet test --logger:"trx;LogFileName=report.trx"