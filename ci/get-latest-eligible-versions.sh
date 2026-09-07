#script to be run inside the ci folder

mkdir venv-kas
cd venv-kas
python3 -m venv .
source bin/activate

cd ..

pip install -r requirements-kas.txt

# comment meta-hf
kas shell ../kas/build-qemux86-64-rolling.yaml --command "bitbake -s | awk '/^python3/ && !/-native/ && !/-cross/ {
    name = $1;
    ver = $2;
    sub(/^python3-/, "", name);
    sub(/^.*:/, "", ver);
    sub(/-r[0-9]+.*$/, "", ver);
    sub(/\+git.*$/, "", ver);
    print name "==" ver
}' > yocto-python3-versions.txt"

#need to relax some stuff in versions : 
# -original name pytorch
# mpmath
# setuptools 

ls -1 ../recipes-python/ > requirements.txt

pip install -r requirements.txt -c yocto-python3-versions.txt --dry-run
