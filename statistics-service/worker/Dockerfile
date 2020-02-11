FROM microsoft/dotnet:2.1.803-sdk

WORKDIR /code

ADD src/Worker /code/src/Worker

RUN  dotnet restore --verbosity diag src/ --configfile src/Worker/nuget.config \
    && dotnet publish -c Release -o ./ src/Worker/ \
    && rm -rf src/ $HOME/.nuget/

CMD dotnet Worker.dll
